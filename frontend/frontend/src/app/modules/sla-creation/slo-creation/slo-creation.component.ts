import { Component, OnInit } from '@angular/core';
import {SloService} from "../../../core/services/slo.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-slo-creation',
  templateUrl: './slo-creation.component.html',
  styleUrls: ['./slo-creation.component.css']
})
export class SloCreationComponent implements OnInit {

  sloTypes = ['Average Response Time', 'Throughput', 'Uptime'];
  selected: string;
  currentSlaId = -1;

  constructor(private sloService: SloService,
              private router: Router,
              private route : ActivatedRoute) {
    this.route.params.subscribe(param => {
      console.log(param['id']);
      this.currentSlaId = param['id'];
    });
  }

  ngOnInit() {
  }
}
