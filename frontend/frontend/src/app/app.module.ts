import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatBadgeModule,
  MatButtonModule,
  MatCardModule, MatCheckboxModule,
  MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule, MatMenuModule, MatNativeDateModule, MatProgressSpinnerModule, MatRadioModule, MatSelectModule,
  MatSidenavModule, MatSnackBar, MatSnackBarModule, MatSortModule, MatStepperModule, MatTableModule,
  MatToolbarModule
} from "@angular/material";
import {AppRoutes} from "./app.routing";
import { NavigationComponent } from './core/navigation/navigation.component';
import { AuthenticationComponent } from './core/authentication/authentication.component';
import {ErrorInterceptor} from "./core/interceptors/error.interceptor";
import {Config} from "./config";
import {LoginComponent} from "./modules/authentication/login/login.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './modules/authentication/register/register.component';
import {JwtInterceptor} from "./core/interceptors/jwt.interceptor";
import {AuthGuard} from "./core/guards/auth.guard";
import {ResponseInterceptor} from "./core/interceptors/response.interceptor";
import { BaseLayoutComponent } from './shared/base-layout/base-layout.component';
import {FlexLayoutModule} from "@angular/flex-layout";
import { SlaCreateComponent } from './modules/sla-creation/sla-create/sla-create.component';
import { SlaOverviewComponent } from './modules/sla-overview/sla-overview/sla-overview.component';
import { SlaItemComponent } from './modules/sla-overview/sla-item/sla-item.component';
import { SlaDetailsComponent } from './modules/sla-overview/sla-details/sla-details.component';
import { SloCreationComponent } from './modules/sla-creation/slo-creation/slo-creation.component';
import { UptimeFormComponent } from './modules/sla-creation/uptime-form/uptime-form.component';
import { ThroughputFormComponent } from './modules/sla-creation/throughput-form/throughput-form.component';
import { SloOverviewComponent } from './modules/sla-creation/slo-overview/slo-overview.component';
import { AverageResponseFormComponent } from './modules/sla-creation/average-response-form/average-response-form.component';
import { UptimeComponent } from './modules/sla-overview/slo-details/uptime/uptime.component';
import { ThroughputComponent } from './modules/sla-overview/slo-details/throughput/throughput.component';
import { AverageResponseTimeComponent } from './modules/sla-overview/slo-details/average-response-time/average-response-time.component';
import { HomeComponent } from './modules/home/home/home.component';
import {NgbDatepickerModule} from "@ng-bootstrap/ng-bootstrap";
import { SlaReviewComponent } from './modules/sla-review/sla-review/sla-review.component';
import { ChartComponent } from './modules/home/chart/chart.component';
import {ChartsModule} from "ng2-charts";
import { ReviewDialogComponent } from './modules/sla-review/modal/review-dialog.component';
import { SlaPropComponent } from './modules/sla-review/modal/sla-prop/sla-prop.component';
import { SloComponent } from './modules/sla-review/modal/slo/slo.component';
import { DeployDialogComponent } from './modules/sla-review/deploy-dialog/deploy-dialog/deploy-dialog.component';
import { ReviseDialogComponent } from './modules/sla-review/revise-dialog/revise/revise-dialog.component';
import { SlaPropEditComponent } from './modules/sla-review/revise-dialog/sla-prop-edit/sla-prop-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AuthenticationComponent,
    LoginComponent,
    RegisterComponent,
    BaseLayoutComponent,
    SlaCreateComponent,
    SlaOverviewComponent,
    SlaItemComponent,
    SlaDetailsComponent,
    SloCreationComponent,
    UptimeFormComponent,
    ThroughputFormComponent,
    SloOverviewComponent,
    AverageResponseFormComponent,
    UptimeComponent,
    ThroughputComponent,
    AverageResponseTimeComponent,
    HomeComponent,
    SlaReviewComponent,
    ChartComponent,
    ReviewDialogComponent,
    SlaPropComponent,
    SloComponent,
    DeployDialogComponent,
    ReviseDialogComponent,
    SlaPropEditComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(AppRoutes),
    MatInputModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    BrowserAnimationsModule,
    MatListModule,
    MatMenuModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
    MatExpansionModule,
    MatButtonModule,
    MatGridListModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatStepperModule,
    MatSelectModule,
    MatSnackBarModule,
    FormsModule,
    NgbDatepickerModule,
    MatSortModule,
    MatBadgeModule,
    ChartsModule,
    MatDialogModule,
    MatCheckboxModule,
    MatRadioModule
  ],
  entryComponents: [
    ReviewDialogComponent,
    DeployDialogComponent,
    ReviseDialogComponent
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ResponseInterceptor, multi: true},
    Config,
    AuthGuard
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
