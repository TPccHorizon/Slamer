import {Component, Input, OnInit} from '@angular/core';
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
  dataUnits = ['B', 'KB', 'MB', 'GB', 'kB', 'mB', 'Gb'];
  operators = ['<', '>', '=', '<=', '>='];
  thresholdUnits = ['ms', 's', 'min', 'h', 'd'];

  constructor(private formBuilder: FormBuilder,
              private sloService: SloService,
              private alertService: AlertService) {
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
    this.sloService.createSlo(slo).pipe(first()).subscribe(res => {
      this.sloService.addSlo(res);
      this.alertService.success('Added new SLO');
    }, error => {
      this.alertService.error('SLO could not be added');
    });
    this.sloService.addSlo(slo);
  }

}
