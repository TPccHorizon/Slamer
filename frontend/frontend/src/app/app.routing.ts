import {Routes} from "@angular/router";
import {AuthenticationComponent} from "./core/authentication/authentication.component";


export const AppRoutes: Routes = [
  {
    path: '',
    component: AuthenticationComponent
  },
  {
    path: 'auth',
    component: AuthenticationComponent
  }
];
