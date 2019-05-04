import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
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
import {Config} from "../config";

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AuthenticationComponent,
    AlertComponent,
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
    MatMenuModule
  ],
  providers: [ErrorInterceptor, Config],
  bootstrap: [AppComponent]
})
export class AppModule { }
