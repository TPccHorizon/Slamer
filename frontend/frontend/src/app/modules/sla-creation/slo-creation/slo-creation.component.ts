import {Component, EventEmitter, OnInit} from '@angular/core';
import {SloService} from "../../../core/services/slo.service";
import {ActivatedRoute, Router} from "@angular/router";
import {BehaviorSubject, Observable} from "rxjs";
import {first} from "rxjs/operators";
import {AlertService} from "../../../core/services/alert.service";

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
              private alertService: AlertService,
              private router: Router,
              private route : ActivatedRoute) {
    this.route.params.subscribe(param => {
      console.log(param['id']);
      this.currentSlaId = param['id'];
    });
    this.sloSubject= new BehaviorSubject<any>(null);
    this.sloCreated = this.sloSubject.asObservable();
  }

  createSlo(slo) {
    console.log("createSlo");
    console.log(slo);
    this.sloService.createSlo(slo).pipe(first()).subscribe(response => {
      this.sloService.addSlo(response);
      this.alertService.success('Added new SLO')
    }, error => {
      this.alertService.error('SLO could not be added');
    });
  }

  finish() {
    this.router.navigate(['/slas/' + this.currentSlaId]);
  }

  ngOnInit() {
  }
}
