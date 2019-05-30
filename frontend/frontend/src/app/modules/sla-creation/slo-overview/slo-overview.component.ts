import {Component, Input, OnInit} from '@angular/core';
import {SloService} from "../../../core/services/slo.service";
import {ServiceLevelObjective} from "../../../shared/models/serviceLevelObjective";
import {BehaviorSubject} from "rxjs";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-slo-overview',
  templateUrl: './slo-overview.component.html',
  styleUrls: ['./slo-overview.component.css']
})
export class SloOverviewComponent implements OnInit {

  createdSlos: ServiceLevelObjective[];
  @Input()
  currentSlaId = -1;
  @Input()
  addedSla = new BehaviorSubject<any>(null);

  constructor(private sloServce: SloService) {
    this.sloServce.slos.pipe(first()).subscribe(() => {
      console.log("SLO addition detected");
      this.loadSLOs();
    })
  }

  ngOnInit() {
    if (this.currentSlaId != -1) {
      this.loadSLOs();
    }

  }

  loadSLOs() {
    console.log("Load SLOs");
    this.sloServce.getSlos(this.currentSlaId).subscribe( slos => {
      this.createdSlos = slos;
    })
  }

}
