import {Component, EventEmitter, OnInit} from '@angular/core';
import {SloService} from "../../../core/services/slo.service";
import {ActivatedRoute, Router} from "@angular/router";
import {BehaviorSubject, Observable} from "rxjs";

@Component({
  selector: 'app-slo-creation',
  templateUrl: './slo-creation.component.html',
  styleUrls: ['./slo-creation.component.css']
})
export class SloCreationComponent implements OnInit {

  sloTypes = ['Average Response Time', 'Throughput', 'Uptime'];
  selected: string;
  currentSlaId = -1;
  sloSubject : BehaviorSubject<any>;
  sloCreated : Observable<any>;

  constructor(private sloService: SloService,
              private router: Router,
              private route : ActivatedRoute) {
    this.route.params.subscribe(param => {
      console.log(param['id']);
      this.currentSlaId = param['id'];
    });
    this.sloSubject= new BehaviorSubject<any>(null);
    this.sloCreated = this.sloSubject.asObservable();
  }

  notify($event) {
    console.log("notify");
    console.log($event);
    this.sloSubject.next($event);
  }

  ngOnInit() {
  }
}
