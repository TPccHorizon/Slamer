import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Uptime} from "../../../shared/models/uptime";
import {SloService} from "../../../core/services/slo.service";

@Component({
  selector: 'app-availability',
  templateUrl: './availability.component.html',
  styleUrls: ['./availability.component.css']
})
export class AvailabilityComponent implements OnInit {

  sloForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private sloService: SloService) {
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
    slo.percentageOfAvailability = this.f.percentageOfAvailability.value;
    this.sloService.addSlo(slo);
  }

}
