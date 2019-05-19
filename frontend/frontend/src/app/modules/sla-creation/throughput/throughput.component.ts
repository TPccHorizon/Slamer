import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SloService} from "../../../core/services/slo.service";
import {Throughput} from "../../../shared/models/throughput";

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

  constructor(private formBuilder: FormBuilder,
              private sloService: SloService) {
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
    let slo = new Throughput();
    slo.name = this.f.name.value;
    slo.sloType= 'Throughput';
    slo.dataSize= this.f.dataSize.value;
    slo.dataUnit= this.f.dataUnit.value;
    slo.operator= this.f.operator.value;
    slo.thresholdValue= this.f.thresholdValue.value;
    slo.thresholdUnit= this.f.thresholdUnit.value;
    this.sloService.addSlo(slo);
  }

}
