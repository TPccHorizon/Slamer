import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Review} from "../../../../shared/models/review";

@Component({
  selector: 'app-revise',
  templateUrl: './revise-dialog.component.html',
  styleUrls: ['./revise-dialog.component.css']
})
export class ReviseDialogComponent {

  constructor(public dialogRef: MatDialogRef<ReviseDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public review: Review) {

  }


  onCancel(): void {
    this.dialogRef.close();
  }

  onSubmit() {
    this.dialogRef.close(this.review);
  }
}
