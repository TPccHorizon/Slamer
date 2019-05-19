import { Component, OnInit } from '@angular/core';
import {SloService} from "../../../core/services/slo.service";
import {ServiceLevelObjective} from "../../../shared/models/serviceLevelObjective";

@Component({
  selector: 'app-slo-overview',
  templateUrl: './slo-overview.component.html',
  styleUrls: ['./slo-overview.component.css']
})
export class SloOverviewComponent implements OnInit {

  createdSlos: ServiceLevelObjective[];

  constructor(private sloServce: SloService) {
  }

  ngOnInit() {
    this.sloServce.getSlos().subscribe( slos => {
      this.createdSlos = slos;
    })
  }

}
