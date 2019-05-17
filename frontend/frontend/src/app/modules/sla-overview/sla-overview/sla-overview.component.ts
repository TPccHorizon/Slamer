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

  step = 0;
  slas: Sla[];

  constructor(private slaService: SlaService) {
    this.slaService.getMySlas().pipe(first())
      .subscribe(data => {
        this.slas = data;
      }, error => {
        console.log(error);
      })
  }

  ngOnInit() {
  }

}
