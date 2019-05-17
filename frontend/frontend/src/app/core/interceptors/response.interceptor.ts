import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import { map } from 'rxjs/operators';
import {AuthenticationService} from "../services/authentication.service";
import {SlaUser} from "../../shared/models/slaUser";

@Injectable()
export class ResponseInterceptor implements HttpInterceptor{

  constructor(private authenticationService: AuthenticationService) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let loginData = req.body;
    return next.handle(req).pipe(map(event => {
      if (event instanceof HttpResponse) {
        let token = event.headers.get("Authorization");
        if (token) {
          localStorage.setItem('currentUser', JSON.stringify(token));
          this.authenticationService.storeJwtToken(loginData, token);
          let newCurrentUser = new SlaUser();
          newCurrentUser.username = loginData.username;
          newCurrentUser.token = token;
        }
      }
      return event;
    }));
  }

}
