import {Routes} from "@angular/router";
import {AuthenticationComponent} from "./core/authentication/authentication.component";
import {LoginComponent} from "./modules/authentication/login/login.component";
import {RegisterComponent} from "./modules/authentication/register/register.component";
import {AuthGuard} from "./core/guards/auth.guard";
import {SlaCreateComponent} from "./modules/sla-creation/sla-create/sla-create.component";
import {SlaOverviewComponent} from "./modules/sla-overview/sla-overview/sla-overview.component";


export const AppRoutes: Routes = [
  {
    path: '',
    component: SlaCreateComponent,
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
  {
    path: 'slas',
    component: SlaOverviewComponent,
    children: [
      {path: 'create', component: SlaCreateComponent}
    ]
  },

  {path: '**', redirectTo: ''}
];
