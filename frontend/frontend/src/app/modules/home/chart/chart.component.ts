import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  labels = ['Definition', 'Negotiation', 'Monitor', 'Termination'];
  chartData = [2, 3, 20, 3];
  chartType = 'doughnut';

  constructor() { }

  ngOnInit() {
  }

}
