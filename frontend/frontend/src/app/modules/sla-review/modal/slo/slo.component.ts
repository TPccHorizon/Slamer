import {Component, Input, OnInit} from '@angular/core';
import {ServiceLevelObjective} from "../../../../shared/models/serviceLevelObjective";

@Component({
  selector: 'app-slo',
  templateUrl: './slo.component.html',
  styleUrls: ['./slo.component.css']
})
export class SloComponent implements OnInit {
  @Input()
  slo: ServiceLevelObjective;

  isAccepted: string;
  constructor() { }



  ngOnInit() {
  }

}
