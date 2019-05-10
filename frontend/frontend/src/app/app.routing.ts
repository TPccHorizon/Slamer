import {Routes} from "@angular/router";
import {AuthenticationComponent} from "./core/authentication/authentication.component";
import {LoginComponent} from "./modules/components/login/login.component";
import {RegisterComponent} from "./modules/components/register/register.component";
import {AuthGuard} from "./core/guards/auth.guard";


export const AppRoutes: Routes = [
  {
    path: '',
    component: AuthenticationComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },

  {path: '**', redirectTo: ''}
];
