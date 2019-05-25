import {Component, Input, OnInit} from '@angular/core';
import {Throughput} from "../../../../shared/models/throughput";

@Component({
  selector: 'app-throughput',
  templateUrl: './throughput.component.html',
  styleUrls: ['./throughput.component.css']
})
export class ThroughputComponent implements OnInit {

  @Input()
  throughput: Throughput;

  constructor() { }

  ngOnInit() {
  }

}
