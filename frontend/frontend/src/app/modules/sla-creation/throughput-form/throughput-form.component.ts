import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SloService} from "../../../core/services/slo.service";
import {Throughput} from "../../../shared/models/throughput";
import {first} from "rxjs/operators";
import {AlertService} from "../../../core/services/alert.service";

@Component({
  selector: 'app-throughput-form',
  templateUrl: './throughput-form.component.html',
  styleUrls: ['./throughput-form.component.css']
})
export class ThroughputFormComponent implements OnInit {

  sloForm: FormGroup;
  submitted = false;

  @Input()
  currentSlaId: number;
  @Output()
  sloCreated = new EventEmitter();

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
    let slo = new Throughput();
    slo.name = this.f.name.value;
    slo.sloType= 'Throughput';
    slo.dataSize= this.f.dataSize.value;
    slo.dataUnit= this.f.dataUnit.value;
    slo.operator= this.f.operator.value;
    slo.slaId = this.currentSlaId;
    slo.thresholdValue= this.f.thresholdValue.value;
    slo.thresholdUnit= this.f.thresholdUnit.value;
    this.sloCreated.emit(slo);
  }

}
