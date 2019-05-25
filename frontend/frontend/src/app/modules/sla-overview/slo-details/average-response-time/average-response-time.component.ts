import {Component, Input, OnInit} from '@angular/core';
import {AverageResponseTime} from "../../../../shared/models/averageResponseTime";

@Component({
  selector: 'app-average-response-time',
  templateUrl: './average-response-time.component.html',
  styleUrls: ['./average-response-time.component.css']
})
export class AverageResponseTimeComponent implements OnInit {

  @Input()
  responseTime: AverageResponseTime;

  constructor() { }

  ngOnInit() {
  }

}
