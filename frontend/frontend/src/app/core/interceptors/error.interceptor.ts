import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {AuthenticationService} from "../services/authentication.service";


@Injectable()
export class ErrorInterceptor implements HttpInterceptor{

  constructor(private authenticationService: AuthenticationService){}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      let error = err.statusText || err.error.message;
      if (err.status === 401) {
        this.authenticationService.logout();
        location.reload(true);
      } else if (err.status === 0) {
        error = 'No connection to Server';
      }
      return throwError(error);
    }))
  }

}
