import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import { map } from 'rxjs/operators';
import {AuthenticationService} from "../services/authentication.service";
import {SlaUser} from "../../shared/models/slaUser";
import {SlaService} from "../services/sla.service";

@Injectable()
export class ResponseInterceptor implements HttpInterceptor{

  constructor(private authenticationService: AuthenticationService, private slaService: SlaService) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let loginData = req.body;
    return next.handle(req).pipe(map(event => {
      if (event instanceof HttpResponse) {
        let token = event.headers.get("Authorization");
        if (token) {
          let tempUser = new SlaUser();
          // store temporary until response with full user arrives. => avoid requests with undefined username
          tempUser.username = loginData.username;
          this.authenticationService.storeJwtToken(tempUser, token);
          this.authenticationService.getMe(loginData.username).subscribe(user => {
            this.authenticationService.storeJwtToken(user, token);
            this.slaService.countNewSLAs();
          });

        }
      }
      return event;
    }));
  }

}
