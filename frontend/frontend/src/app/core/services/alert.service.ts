import {Injectable, NgZone} from '@angular/core';
import {Observable, Subject} from "rxjs";
import {NavigationStart, Router} from "@angular/router";
import {MatSnackBar, MatSnackBarConfig} from "@angular/material";

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor(private snackbar: MatSnackBar,
              private zone: NgZone) {
  }

  success(message: string, isHandset = false) {
    this.show(message, {
      duration: 2000,
      panelClass: 'success-notification-overlay'
    }, isHandset);
  }

  error(message: string, isHandset = false) {
    this.show(message, {
      duration: 2000,
      panelClass: 'error-notification-overlay'
    }, isHandset);
  }

  private show(message: string, configuration: MatSnackBarConfig, isHandset?:boolean) {
    if (!isHandset) {
      configuration.horizontalPosition = 'right';
      configuration.verticalPosition = 'top';
    }
    this.zone.run(() => this.snackbar.open(message, null, configuration));
  }
}
