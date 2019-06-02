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

@Component({
  selector: 'app-sla-review',
  templateUrl: './sla-review.component.html',
  styleUrls: ['./sla-review.component.css']
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
              private alertService: AlertService) {
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
      this.openDeployMode();
    }
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

  openDeployMode() {
    const dialogRef = this.dialog.open(DeployDialogComponent, {
      minWidth: 400
    });

    dialogRef.afterClosed().subscribe(doDeploy => {
      if (doDeploy) {
        //TODO: service to deploy smart contract
        console.log("Deploy SLA!");
        this.alertService.success("SLA has been deployed");
      }
    });
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
    } else {
      label = '-';
    }
    return label;
  }

}
