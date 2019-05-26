import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Uptime} from "../../../shared/models/uptime";
import {SloService} from "../../../core/services/slo.service";
import {first} from "rxjs/operators";
import {AlertService} from "../../../core/services/alert.service";

@Component({
  selector: 'app-uptime-form',
  templateUrl: './uptime-form.component.html',
  styleUrls: ['./uptime-form.component.css']
})
export class UptimeFormComponent implements OnInit {

  sloForm: FormGroup;
  submitted = false;
  @Input()
  currentSlaId: number;
  @Output()
  sloCreated = new EventEmitter();


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
    slo.slaId = this.currentSlaId;
    slo.sloType= 'Uptime';
    slo.percentageOfAvailability = this.toPercentValue(this.f.percentageOfAvailability.value);
    console.log(slo.percentageOfAvailability);
    this.sloService.createSlo(slo).pipe(first()).subscribe(response => {
      this.sloService.addSlo(response);
      this.sloCreated.emit(slo);
      this.alertService.success('Added new SLO')
    }, error => {
      this.alertService.error('SLO could not be added');
    });
  }

  toPercentValue(value: number) {
    return value / 100;
  }

}
