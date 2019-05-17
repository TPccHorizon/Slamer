import {Component, Input, OnInit} from '@angular/core';
import {Sla} from "../../../shared/models/sla";

@Component({
  selector: 'app-sla-item',
  templateUrl: './sla-item.component.html',
  styleUrls: ['./sla-item.component.css']
})
export class SlaItemComponent implements OnInit {

  @Input()
  sla: Sla;

  constructor() { }

  ngOnInit() {
  }

  showDetails(slaId: number) {
    console.log("Show SLA with id: " + slaId);
  }

}
