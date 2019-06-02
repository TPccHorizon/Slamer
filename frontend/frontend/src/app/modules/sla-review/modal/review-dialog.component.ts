import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {SlaAndParties} from "../../../shared/models/slaAndParties";
import {Review} from "../../../shared/models/review";
import {SlaReview} from "../../../shared/models/SlaReview";

@Component({
  selector: 'app-modal',
  templateUrl: './review-dialog.component.html',
  styleUrls: ['./review-dialog.component.css']
})
export class ReviewDialogComponent {


  slosReviewed = new Array<boolean>(0);
  readyToSubmit = false;
  review = new Review();

  constructor(public dialogRef: MatDialogRef<ReviewDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public sla: SlaAndParties) {
      this.mapToReview(sla);
  }

  mapToReview(sla: SlaAndParties) {
    this.review.slaId = sla.id;
    let validFrom = new SlaReview();
    validFrom.accepted=true;
    validFrom.property='validFrom';
    validFrom.comment = '';
    validFrom.value = sla.validFrom;
    this.review.validFrom = validFrom;
    let validTo = new SlaReview();
    validTo.accepted=true;
    validTo.property='validTo';
    validTo.comment = '';
    validTo.value = sla.validTo;
    this.review.validTo = validTo;
    let servicePrice = new SlaReview();
    servicePrice.accepted = true;
    servicePrice.property = 'servicePrice';
    servicePrice.comment = '';
    servicePrice.value = sla.servicePrice;
    this.review.servicePrice = servicePrice;
    this.review.slos = sla.slos;
    this.review.slos.forEach(function (slo) {
      slo.accepted = true;
      slo.slaId = sla.id;
    })
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  onAnswer(answered: boolean) {
    let numberOfAnswers = this.slosReviewed.push(answered);
    if (numberOfAnswers === this.sla.slos.length + 3) {
      this.readyToSubmit = true;
    }
  }

  onSubmit() {
    this.dialogRef.close(this.review);
  }

}
