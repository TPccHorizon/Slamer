import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {SlaAndParties} from "../../../../shared/models/slaAndParties";

@Component({
  selector: 'app-deploy-dialog',
  templateUrl: './deploy-dialog.component.html',
  styleUrls: ['./deploy-dialog.component.css']
})
export class DeployDialogComponent {

  constructor(public dialogRef: MatDialogRef<DeployDialogComponent>) { }

  onNoClick(): void {
    this.dialogRef.close(false);
  }

  onDeploy(): void {
    this.dialogRef.close(true);
  }

}
