import { Component, OnInit } from '@angular/core';
import {ChartType} from "chart.js";
import {SlaService} from "../../../core/services/sla.service";
import {first} from "rxjs/operators";
import {Report} from "../../../shared/models/Report";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  labels = ['Definition', 'Establishment', 'Monitoring', 'Termination', 'Penalty Enforcement'];
  chartData : number[];
  chartType: ChartType = 'doughnut';
  isReady = false;

  constructor(private slaService: SlaService) {
    this.slaService.getReport().pipe(first()).subscribe(report => {
      this.chartData = this.mapToArray(report);
      this.isReady = true;
    })
  }

  ngOnInit() {
  }

  mapToArray(report: Report) {
    return [report.definition, report.negotiation, report.monitoring, report.termination, report.penaltyEnforcement]
  }

}
