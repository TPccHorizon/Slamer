import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCardModule,
  MatDatepickerModule, MatExpansionModule, MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule, MatMenuModule, MatNativeDateModule, MatProgressSpinnerModule, MatSelectModule,
  MatSidenavModule, MatSnackBar, MatSnackBarModule, MatStepperModule, MatTableModule,
  MatToolbarModule
} from "@angular/material";
import {AppRoutes} from "./app.routing";
import { NavigationComponent } from './core/navigation/navigation.component';
import { AuthenticationComponent } from './core/authentication/authentication.component';
import { AlertComponent } from './shared/alert/alert.component';
import {ErrorInterceptor} from "./core/interceptors/error.interceptor";
import {Config} from "./config";
import {LoginComponent} from "./modules/authentication/login/login.component";
import {ReactiveFormsModule} from "@angular/forms";
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
import { SlaStepperComponent } from './modules/sla-creation/sla-stepper/sla-stepper.component';
import { SloCreationComponent } from './modules/sla-creation/slo-creation/slo-creation.component';
import { AvailabilityComponent } from './modules/sla-creation/availability/availability.component';
import { ThroughputComponent } from './modules/sla-creation/throughput/throughput.component';
import { SloOverviewComponent } from './modules/sla-creation/slo-overview/slo-overview.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AuthenticationComponent,
    AlertComponent,
    LoginComponent,
    RegisterComponent,
    BaseLayoutComponent,
    SlaCreateComponent,
    SlaOverviewComponent,
    SlaItemComponent,
    SlaDetailsComponent,
    SlaStepperComponent,
    SloCreationComponent,
    AvailabilityComponent,
    ThroughputComponent,
    SloOverviewComponent,
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
    MatSnackBarModule
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
