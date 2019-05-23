import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Uptime} from "../../../shared/models/uptime";
import {SloService} from "../../../core/services/slo.service";
import {first} from "rxjs/operators";
import {AlertService} from "../../../core/services/alert.service";

@Component({
  selector: 'app-availability',
  templateUrl: './availability.component.html',
  styleUrls: ['./availability.component.css']
})
export class AvailabilityComponent implements OnInit {

  sloForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private sloService: SloService,
              private alertService: AlertService) {
    this.sloForm = this.formBuilder.group({
      name: ['', Validators.required],
      percentageOfAvailability: ['', Validators.required],
    });
  }

  get f() {return this.sloForm.controls;}

  ngOnInit() {
  }

  onSubmit() {
    let slo = new Uptime();
    slo.name = this.f.name.value;
    slo.sloType= 'Uptime';
    slo.percentageOfAvailability = this.toPercentValue(this.f.percentageOfAvailability.value);
    console.log(slo.percentageOfAvailability);
    this.sloService.createSlo(slo).pipe(first()).subscribe(response => {
      this.sloService.addSlo(response);
      this.alertService.success('Added new SLO')
    }, error => {
      this.alertService.error('SLO could not be added');
    });
  }

  toPercentValue(value: number) {
    return value / 100;
  }

}
