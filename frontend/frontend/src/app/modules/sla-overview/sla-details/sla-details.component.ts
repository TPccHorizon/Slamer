import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {SlaService} from "../../../core/services/sla.service";
import {first} from "rxjs/operators";
import {SlaAndParties} from "../../../shared/models/slaAndParties";
import {SLA_STATES} from "../../../shared/constants/sla-states";
import {AlertService} from "../../../core/services/alert.service";

@Component({
  selector: 'app-sla-details',
  templateUrl: './sla-details.component.html',
  styleUrls: ['./sla-details.component.scss']
})
export class SlaDetailsComponent implements OnInit {

  sla: SlaAndParties;
  approvalColumns: string[] = ['name', 'role', 'signature', 'date'];
  dataSource: any[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private slaService: SlaService,
              private alertService: AlertService) { }

  ngOnInit() {
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

  sendToCustomerForReview() {
    this.slaService.updateSLAStatus(this.sla.status, this.sla.id).pipe(first())
      .subscribe(result => {
        this.assignResponse(result);
        this.alertService.success("Sent SLA to " + this.sla.serviceCustomer.partyName);
      });
  }

  isIdentified() {
    return this.sla.status === SLA_STATES.IDENTIFIED;
  }

  private assignResponse(response) {
    this.sla = response;
    this.dataSource= [
      {name: this.sla.serviceProvider.partyName, role: 'Service Provider', signature: '', date: ''},
      {name: this.sla.serviceCustomer.partyName, role: 'Customer', signature: '', date: ''}
    ];
  }

}
