import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {AuthenticationService} from "../services/authentication.service";
import {MatSnackBar} from "@angular/material";


@Injectable()
export class ErrorInterceptor implements HttpInterceptor{

  constructor(private authenticationService: AuthenticationService,
              private snackBar: MatSnackBar){}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      let error = err.error.message || err.statusText;
      if (err.status === 401) {
        this.authenticationService.logout();
        location.reload(true);
      } else if (err.status === 0) {
        error = 'No connection to Server';
      }
      this.openSnackBar(error);
      return throwError(error);
    }))
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close',{
      duration: 3000
    });
  }

}
