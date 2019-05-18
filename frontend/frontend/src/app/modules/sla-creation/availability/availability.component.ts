import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-availability',
  templateUrl: './availability.component.html',
  styleUrls: ['./availability.component.css']
})
export class AvailabilityComponent implements OnInit {

  sloForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder) {
    this.sloForm = this.formBuilder.group({
      name: ['', Validators.required],
      percentageOfAvailability: ['', Validators.required],
    });
  }

  get f() {return this.sloForm.controls;}

  ngOnInit() {
  }

  onSubmit() {
    console.log("submit slo");
  }

}
