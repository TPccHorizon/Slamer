import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {AuthenticationService} from "../services/authentication.service";

@Injectable()
export class AuthGuard implements CanActivate{

  constructor(private router: Router, private authenticationService: AuthenticationService) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const curentUser = this.authenticationService.currentUserValue;
    if (curentUser) {
      return true;
    }
    this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
    return false;
  }
}
