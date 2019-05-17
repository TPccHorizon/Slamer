import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {SlaService} from "../../../core/services/sla.service";
import {Sla} from "../../../shared/models/sla";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-sla-details',
  templateUrl: './sla-details.component.html',
  styleUrls: ['./sla-details.component.css']
})
export class SlaDetailsComponent implements OnInit {

  sla: Sla;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private slaService: SlaService) { }

  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');
    this.sla = this.slaService.getSla(id);
  }

}
