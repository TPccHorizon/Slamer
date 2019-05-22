import { Component, OnInit } from '@angular/core';
import {SloService} from "../../../core/services/slo.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-slo-creation',
  templateUrl: './slo-creation.component.html',
  styleUrls: ['./slo-creation.component.css']
})
export class SloCreationComponent implements OnInit {

  sloTypes = ['Average Response Time', 'Throughput', 'Uptime'];
  selected: string;
  currentSlaId = -1;

  constructor(private sloService: SloService, private router: Router) {
    this.currentSlaId = this.sloService.getCurrentSlaId();
    console.log('Current SLA: ' + this.currentSlaId);
    if (this.currentSlaId === -1 || this.currentSlaId === undefined) {
      // go back to sla creation if there is no current SLA
      this.router.navigate(['/slas/create'])
    }
  }

  ngOnInit() {
  }
}
