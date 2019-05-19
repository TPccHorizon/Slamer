import { Component, OnInit } from '@angular/core';
import {SlaService} from "../../../core/services/sla.service";
import {Sla} from "../../../shared/models/sla";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-sla-overview',
  templateUrl: './sla-overview.component.html',
  styleUrls: ['./sla-overview.component.css']
})
export class SlaOverviewComponent implements OnInit {

  pageTitle = 'Manage SLAs';
  loading = false;
  slas: Sla[];

  constructor(private slaService: SlaService) {
    this.loading = true;
    this.slaService.getMySlas().pipe(first())
      .subscribe(data => {
        this.slas = data;
        this.loading = false;
      }, error => {
        console.log(error);
        this.loading = false;
      })
  }

  ngOnInit() {
  }

}
