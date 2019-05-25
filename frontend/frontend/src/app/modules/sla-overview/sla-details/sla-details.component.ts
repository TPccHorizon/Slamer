import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {SlaService} from "../../../core/services/sla.service";
import {Sla} from "../../../shared/models/sla";
import {first, switchMap} from "rxjs/operators";
import {SlaAndParties} from "../../../shared/models/slaAndParties";

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
              private slaService: SlaService) { }

  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');
    this.slaService.getSlaWithParties(id as unknown as number).pipe(first())
      .subscribe(result => {
        console.log(result);
        this.sla = result;
        this.dataSource= [
          {name: this.sla.serviceProvider.partyName, role: 'Service Provider', signature: '', date: ''},
          {name: this.sla.serviceCustomer.partyName, role: 'Customer', signature: '', date: ''}
        ];
      }, error => {
        console.log(error);
      });
  }

}
