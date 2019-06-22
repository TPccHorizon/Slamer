import {Component, Input, OnInit} from '@angular/core';
import {ServiceLevelObjective} from "../../../../shared/models/serviceLevelObjective";

@Component({
  selector: 'app-slo-edit',
  templateUrl: './slo-edit.component.html',
  styleUrls: ['./slo-edit.component.css']
})
export class SloEditComponent implements OnInit {

  @Input()
  slo: ServiceLevelObjective;

  constructor() { }

  ngOnInit() {
  }

}
