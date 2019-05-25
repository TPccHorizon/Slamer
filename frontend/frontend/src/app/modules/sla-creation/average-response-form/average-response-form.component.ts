import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SloService} from "../../../core/services/slo.service";
import {AlertService} from "../../../core/services/alert.service";
import {Uptime} from "../../../shared/models/uptime";
import {first} from "rxjs/operators";
import {AverageResponseTime} from "../../../shared/models/averageResponseTime";

@Component({
  selector: 'app-average-response-form',
  templateUrl: './average-response-form.component.html',
  styleUrls: ['./average-response-form.component.css']
})
export class AverageResponseFormComponent implements OnInit {

  sloForm: FormGroup;
  submitted = false;
  timeUnits = ['ms', 's', 'min', 'h', 'd'];

  constructor(private formBuilder: FormBuilder,
              private sloService: SloService,
              private alertService: AlertService) {
    this.sloForm = this.formBuilder.group({
      name: ['', Validators.required],
      averageResponseTimeValue: ['', Validators.required],
      timeUnit: ['', Validators.required]
    })
  }

  get f() {return this.sloForm.controls;}

  ngOnInit() {
  }

  onSubmit() {
    let slo = new AverageResponseTime();
    // slo = this.sloForm.value;
    slo.name = this.f.name.value;
    slo.sloType = 'Average Response Time';
    slo.averageResponseTime = this.f.averageResponseTimeValue.value;
    slo.timeUnit = this.f.timeUnit.value;
    this.sloService.createSlo(slo).pipe(first()).subscribe(response => {
      this.sloService.addSlo(response);
      this.alertService.success('Added new SLO')
    }, error => {
      this.alertService.error('SLO could not be added');
    });
  }

}
