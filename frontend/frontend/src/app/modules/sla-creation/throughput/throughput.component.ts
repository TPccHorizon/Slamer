import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-throughput',
  templateUrl: './throughput.component.html',
  styleUrls: ['./throughput.component.css']
})
export class ThroughputComponent implements OnInit {

  sloForm: FormGroup;
  submitted = false;

  dataUnits = ['B', 'KB', 'MB', 'GB', 'kB', 'mB', 'Gb'];
  operators = ['<', '>', '=', '<=', '>='];
  thresholdUnits = ['ms', 's', 'min', 'h', 'd'];

  constructor(private formBuilder: FormBuilder) {
    this.sloForm = this.formBuilder.group({
      name: ['', Validators.required],
      dataSize: ['', Validators.required],
      dataUnit: ['', Validators.required],
      operator: ['', Validators.required],
      thresholdValue: ['', Validators.required],
      thresholdUnit: ['', Validators.required],
    });
  }

  get f() {return this.sloForm.controls;}

  ngOnInit() {
  }

  onSubmit() {
    console.log("submit slo");
  }

}
