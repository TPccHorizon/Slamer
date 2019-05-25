import {Component, Input, OnInit} from '@angular/core';
import {SloService} from "../../../core/services/slo.service";
import {ServiceLevelObjective} from "../../../shared/models/serviceLevelObjective";

@Component({
  selector: 'app-slo-overview',
  templateUrl: './slo-overview.component.html',
  styleUrls: ['./slo-overview.component.css']
})
export class SloOverviewComponent implements OnInit {

  createdSlos: ServiceLevelObjective[];
  @Input()
  currentSlaId = -1;

  constructor(private sloServce: SloService) {
  }

  ngOnInit() {
    console.log("on Init");
    console.log("SLA ID: " + this.currentSlaId);
    if (this.currentSlaId != -1) {
      this.loadSLOs();
    }

  }

  loadSLOs() {
    this.sloServce.getSlos(this.currentSlaId).subscribe( slos => {
      this.createdSlos = slos;
    })
  }

}
