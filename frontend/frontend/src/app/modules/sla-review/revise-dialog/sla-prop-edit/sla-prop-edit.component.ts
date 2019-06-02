import {Component, Input, OnInit} from '@angular/core';
import {SlaReview} from "../../../../shared/models/SlaReview";

@Component({
  selector: 'app-sla-prop-edit',
  templateUrl: './sla-prop-edit.component.html',
  styleUrls: ['./sla-prop-edit.component.css']
})
export class SlaPropEditComponent implements OnInit {

  @Input()
  property: SlaReview;

  constructor() { }

  ngOnInit() {
  }

}
