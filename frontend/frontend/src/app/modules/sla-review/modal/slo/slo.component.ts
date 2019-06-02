import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ServiceLevelObjective} from "../../../../shared/models/serviceLevelObjective";

@Component({
  selector: 'app-slo',
  templateUrl: './slo.component.html',
  styleUrls: ['./slo.component.css']
})
export class SloComponent {
  @Input()
  slo: ServiceLevelObjective;

  @Output()
  selectedChoice = new EventEmitter<boolean>();
  constructor() { }

  select() {
    this.selectedChoice.emit(true);
  }

}
