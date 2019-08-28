import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {SlaAndParties} from "../../../shared/models/slaAndParties";

@Component({
  selector: 'app-activate-dialog',
  templateUrl: './activate-dialog.component.html',
  styleUrls: ['./activate-dialog.component.css']
})
export class ActivateDialogComponent {

  constructor(public dialogRef: MatDialogRef<ActivateDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public sla: SlaAndParties) { }

  onNoClick(): void {
    this.dialogRef.close(false);
  }

  onActivate(): void {
    this.dialogRef.close(true);
  }

}
