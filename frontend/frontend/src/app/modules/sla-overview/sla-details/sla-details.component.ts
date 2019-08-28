import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {SlaService} from "../../../core/services/sla.service";
import {first} from "rxjs/operators";
import {SlaAndParties} from "../../../shared/models/slaAndParties";
import {SLA_STATES} from "../../../shared/constants/sla-states";
import {AlertService} from "../../../core/services/alert.service";
import {init} from "protractor/built/launcher";
import {BalanceService} from "../../../core/services/balance.service";

@Component({
  selector: 'app-sla-details',
  templateUrl: './sla-details.component.html',
  styleUrls: ['./sla-details.component.scss']
})
export class SlaDetailsComponent implements OnInit {

  sla: SlaAndParties;
  approvalColumns: string[] = ['name', 'role'];
  dataSource: any[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private slaService: SlaService,
              private alertService: AlertService,
              private balanceService: BalanceService) { }

  ngOnInit() {
    this.init();
  }

  terminateByViolation() {
    this.slaService.terminate(this.sla.id).subscribe(res => {
      this.init();
      this.balanceService.getBalance();
      this.alertService.success("SLA has been terminated");
    }, error => {
      this.alertService.error(error);
      this.init();
      this.balanceService.getBalance();
    });
  }

  sendToCustomerForReview() {
    this.slaService.updateSLAStatus(this.sla.status, this.sla.id).pipe(first())
      .subscribe(result => {
        this.assignResponse(result);
        this.alertService.success("Sent SLA to " + this.sla.serviceCustomer.partyName);
      });
  }

  init() {
    let id = this.route.snapshot.paramMap.get('id');
    this.slaService.getSlaWithParties(id as unknown as number).pipe(first())
      .subscribe(result => {
        console.log(result);
        this.assignResponse(result);
      }, error => {
        console.log(error);
      });
  }

  isServiceProvider() {
    let me = JSON.parse(localStorage.getItem('currentUser'));
    return this.sla.serviceProvider.id === me.id;
  }

  isCustomer() {
    let me = JSON.parse(localStorage.getItem('currentUser'));
    return this.sla.serviceCustomer.id === me.id;
  }


  isIdentified() {
    return this.sla.status === SLA_STATES.IDENTIFIED;
  }

  isActive() {
    return this.sla.status === SLA_STATES.ACTIVE;
  }

  private assignResponse(response) {
    this.sla = response;
    this.dataSource= [
      {name: this.sla.serviceProvider.partyName, role: 'Service Provider'},
      {name: this.sla.serviceCustomer.partyName, role: 'Customer'}
    ];
  }

  copyToClipboard(val: string){
    let selBox = document.createElement('textarea');
    selBox.style.position = 'fixed';
    selBox.style.left = '0';
    selBox.style.top = '0';
    selBox.style.opacity = '0';
    selBox.value = val;
    document.body.appendChild(selBox);
    selBox.focus();
    selBox.select();
    document.execCommand('copy');
    document.body.removeChild(selBox);
  }

}
