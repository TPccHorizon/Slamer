import {Component, Input, OnInit} from '@angular/core';
import {ServiceLevelObjective} from "../../../../shared/models/serviceLevelObjective";

@Component({
  selector: 'app-slo',
  templateUrl: './slo.component.html',
  styleUrls: ['./slo.component.css']
})
export class SloComponent implements OnInit {

  constructor() { }

  @Input()
  slo: ServiceLevelObjective;

  ngOnInit() {
  }

}
