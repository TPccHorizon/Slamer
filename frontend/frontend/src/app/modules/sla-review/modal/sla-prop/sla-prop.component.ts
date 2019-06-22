import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SlaReview} from "../../../../shared/models/SlaReview";

@Component({
  selector: 'app-sla-prop',
  templateUrl: './sla-prop.component.html',
  styleUrls: ['./sla-prop.component.css']
})
export class SlaPropComponent {

  @Input()
  property: SlaReview;

  @Input()
  propertyName: string;
  @Output()
  selectedChoice = new EventEmitter<boolean>();


  constructor() { }

  select() {
    this.selectedChoice.emit(true);
  }

}
