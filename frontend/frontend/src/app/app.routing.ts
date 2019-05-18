import {Routes} from "@angular/router";
import {AuthenticationComponent} from "./core/authentication/authentication.component";
import {LoginComponent} from "./modules/authentication/login/login.component";
import {RegisterComponent} from "./modules/authentication/register/register.component";
import {AuthGuard} from "./core/guards/auth.guard";
import {SlaCreateComponent} from "./modules/sla-creation/sla-create/sla-create.component";
import {SlaOverviewComponent} from "./modules/sla-overview/sla-overview/sla-overview.component";
import {SlaDetailsComponent} from "./modules/sla-overview/sla-details/sla-details.component";
import {SlaStepperComponent} from "./modules/sla-creation/sla-stepper/sla-stepper.component";


export const AppRoutes: Routes = [
  {
    path: '',
    component: SlaOverviewComponent,
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
    path: 'slas/',
    component: SlaOverviewComponent
  },
  {
    path: 'slas/create', component: SlaStepperComponent
  },
  {
    path: 'slas/:id', component: SlaDetailsComponent
  },

  {path: '**', redirectTo: ''}
];
