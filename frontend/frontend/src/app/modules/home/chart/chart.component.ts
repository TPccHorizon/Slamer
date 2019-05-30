import { Component, OnInit } from '@angular/core';
import {ChartType} from "chart.js";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  labels = ['Definition', 'Negotiation', 'Monitoring', 'Management', 'Termination', 'Penalty Enforcement'];
  chartData = [2, 3, 10, 3, 3, 2];
  chartType: ChartType = 'doughnut';

  constructor() { }

  ngOnInit() {
  }

}
