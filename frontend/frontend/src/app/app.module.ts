import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatIconModule,
  MatInputModule,
  MatListModule, MatMenuModule,
  MatSidenavModule,
  MatToolbarModule
} from "@angular/material";
import {AppRoutes} from "./app.routing";
import { NavigationComponent } from './core/navigation/navigation.component';
import { AuthenticationComponent } from './core/authentication/authentication.component';
import { AlertComponent } from './modules/components/alert/alert.component';
import {ErrorInterceptor} from "./core/interceptors/error.interceptor";
import {Config} from "./config";
import {LoginComponent} from "./modules/components/login/login.component";
import {ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './modules/components/register/register.component';
import {JwtInterceptor} from "./core/interceptors/jwt.interceptor";
import {AuthGuard} from "./core/guards/auth.guard";
import {ResponseInterceptor} from "./core/interceptors/response.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AuthenticationComponent,
    AlertComponent,
    LoginComponent,
    RegisterComponent,
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
    ReactiveFormsModule
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
