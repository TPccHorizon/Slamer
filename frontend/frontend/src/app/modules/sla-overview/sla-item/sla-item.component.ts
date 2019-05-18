import {Component, Input, OnInit} from '@angular/core';
import {Sla} from "../../../shared/models/sla";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sla-item',
  templateUrl: './sla-item.component.html',
  styleUrls: ['./sla-item.component.css']
})
export class SlaItemComponent implements OnInit {

  @Input()
  sla: Sla;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  showDetails(slaId: number) {
    console.log("Show SLA with id: " + slaId);
    this.router.navigate([`/slas/${slaId}`]);
  }

}
