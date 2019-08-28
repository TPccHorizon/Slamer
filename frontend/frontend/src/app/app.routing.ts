import {Routes} from "@angular/router";
import {LoginComponent} from "./modules/authentication/login/login.component";
import {RegisterComponent} from "./modules/authentication/register/register.component";
import {AuthGuard} from "./core/guards/auth.guard";
import {SlaCreateComponent} from "./modules/sla-creation/sla-create/sla-create.component";
import {SlaOverviewComponent} from "./modules/sla-overview/sla-overview/sla-overview.component";
import {SlaDetailsComponent} from "./modules/sla-overview/sla-details/sla-details.component";
import {SloCreationComponent} from "./modules/sla-creation/slo-creation/slo-creation.component";
import {HomeComponent} from "./modules/home/home/home.component";
import {SlaReviewComponent} from "./modules/sla-review/sla-review/sla-review.component";
import {SettingsComponent} from "./modules/settings/settings/settings.component";
import {MonitoringRegistrationComponent} from "./modules/sla-creation/monitoring-registration/monitoring-registration.component";


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
    path: 'settings',
    component: SettingsComponent
  },
  {
    path: 'new', component: SlaCreateComponent
  },
  {
    path: 'slas',
    component: SlaOverviewComponent
  },
  {
    path: 'slas/review', component: SlaReviewComponent
  },
  {
    path: 'slas/:id', component: SlaDetailsComponent
  },
  {
    path: 'slas/:id/slo', component: SloCreationComponent
  },
  {
    path: 'slas/:id/monitoring', component: MonitoringRegistrationComponent
  },


  {path: '**', redirectTo: ''}
];
