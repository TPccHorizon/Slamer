import {Component, OnInit, ViewChild} from '@angular/core';
import {Sla} from "../../../shared/models/sla";
import {MatDialog, MatSort, MatTableDataSource, Sort} from "@angular/material";
import {SlaService} from "../../../core/services/sla.service";
import {SortingService} from "../../../core/services/sorting.service";
import {first} from "rxjs/operators";
import {SlaAndParties} from "../../../shared/models/slaAndParties";
import {ReviewDialogComponent} from "../modal/review-dialog.component";
import {SloService} from "../../../core/services/slo.service";
import {ReviewService} from "../../../core/services/review.service";
import {AlertService} from "../../../core/services/alert.service";
import {SLA_STATES} from "../../../shared/constants/sla-states";
import {DeployDialogComponent} from "../deploy-dialog/deploy-dialog/deploy-dialog.component";
import {Review} from "../../../shared/models/review";
import {ReviseDialogComponent} from "../revise-dialog/revise/revise-dialog.component";
import {HttpClient} from "@angular/common/http";
import {Config} from "../../../config";
import { ActivateDialogComponent } from '../activate-dialog/activate-dialog.component';
import {BalanceService} from "../../../core/services/balance.service";

@Component({
  selector: 'app-sla-review',
  templateUrl: './sla-review.component.html',
  styleUrls: ['./sla-review.component.scss']
})
export class SlaReviewComponent  {

  loading = false;
  slas: SlaAndParties[] = null;
  dataSource : MatTableDataSource<Sla>;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private slaService: SlaService,
              private sloService: SloService,
              private sorter: SortingService,
              private dialog: MatDialog,
              private reviewService: ReviewService,
              private alertService: AlertService,
              private balanceService: BalanceService) {
    this.loading = true;
    this.refreshList();
  }

  sortSlas(sort: Sort) {
    console.log("SortSlas");
    this.slas = this.sorter.sortData(this.slas, sort);
  }

  openDialog(sla: SlaAndParties) {
    if (sla.status === SLA_STATES.REQUESTED) {
      this.openReviewMode(sla);
    } else if (sla.status === SLA_STATES.ACCEPTED) {
      this.openDeployMode(sla);
    } else if (sla.status === SLA_STATES.REJECTED) {
      this.openRevisionMode(sla);
    } else if (sla.status === SLA_STATES.DEPLOYMENT) {
      this.openActivateMode(sla);
    }
  }

  openActivateMode(sla: SlaAndParties) {
    const dialogRef = this.dialog.open(ActivateDialogComponent, {
      minWidth: 400,
      data: sla,
    });

    dialogRef.afterClosed().subscribe(doActivate => {
      if (doActivate) {
        console.log("Activate SLA");
        this.slaService.activateSLA(sla).subscribe(success => {
          if (success) {
            this.alertService.success("SLA has been activated");
            this.balanceService.getBalance();
            this.refreshList();
          } else {
            this.alertService.error("Something went wrong");
          }
        }, error => {
          this.alertService.error("Unexpected error");
        });
        this.delay(1000).then(any => {
          this.refreshList();
        });
      }
    })

  }

  openReviewMode(sla: SlaAndParties) {
    this.sloService.getSlos(sla.id).pipe(first()).subscribe(slos => {
      sla.slos = slos;
      const dialogRef = this.dialog.open(ReviewDialogComponent, {
        data: sla,
        minWidth: 850
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log("Closed dialog");
        console.log(result);
        if (result != null) {
          this.reviewService.createReview(result).pipe(first())
            .subscribe(created => {
              console.log(created);
              this.alertService.success("Sent Review to " + sla.serviceProvider.partyName);
              this.refreshList();
              this.slaService.countNewSLAs();
            }, error => {
              console.log(error);
              this.alertService.error("An error occurred");
            });

        }

      });
    });
  }

  openDeployMode(sla: Sla) {
    const dialogRef = this.dialog.open(DeployDialogComponent, {
      minWidth: 400,
    });

    dialogRef.afterClosed().subscribe(doDeploy => {
      if (doDeploy) {
        console.log("Deploy SLA!");
        this.slaService.deploy(sla).pipe(first()).subscribe(res => {
          console.log(res);
          this.refreshList();
          this.balanceService.getBalance();
          this.alertService.success("SLA has been deployed");
        }, error => {
          this.alertService.error("SLA could not be deployed");
        });
        this.delay(1000).then(any => {
          this.refreshList();
        });
      }
    });
  }

  openRevisionMode(sla: SlaAndParties) {
    let review = new Review();
    this.reviewService.getReview(sla.id).pipe(first()).subscribe(response => {
      review = response;
      console.log(response);
      const dialogRef = this.dialog.open(ReviseDialogComponent, {
        data: review,
        minWidth: 850
      });

      dialogRef.afterClosed().subscribe(updatedReview => {
        if (updatedReview != null) {
          console.log("REVISED");
          this.reviewService.reviseReview(updatedReview).pipe(first())
            .subscribe(created => {
              console.log(created);
              this.alertService.success("Sent Revision to " + sla.serviceCustomer.partyName);
              this.refreshList();
              this.slaService.countNewSLAs();
            }, error => {
              console.log(error);
              this.alertService.error("An error occurred");
            });

        }
      });

    }, error => {
      console.log(error);
      this.alertService.error('Could not get Review Data');
    })
  }

  refreshList() {
    this.slaService.getSlasForReview().pipe(first())
      .subscribe(data => {
        this.slas = data;
        this.dataSource = new MatTableDataSource<Sla>(this.slas);
        this.dataSource.sort = this.sort;
        this.loading = false;
      }, error => {
        console.log(error);
        this.loading = false;
      })
  }

  getButtonLabel(sla: SlaAndParties) {
    let status = sla.status;
    let label = '';
    if (status === SLA_STATES.REQUESTED) {
      label = 'Review';
    } else if (status === SLA_STATES.ACCEPTED) {
      label = 'Deploy';
    } else if (status === SLA_STATES.REJECTED) {
      label = 'Revise';
    } else if (status === SLA_STATES.DEPLOYMENT) {
      label = 'Activate';
    } else if (this.isPending(sla)) {
      label = 'Pending';
    }
    return label;
  }

  isPending(sla: SlaAndParties) {
    return sla.status === SLA_STATES.PENDING_DEPLOYMENT || sla.status === SLA_STATES.PENDING_DEPOSIT
  }

  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("slept"));
  }

}
