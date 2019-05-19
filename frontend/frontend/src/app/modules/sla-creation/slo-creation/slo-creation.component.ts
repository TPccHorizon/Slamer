import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AlertService} from "../../../core/services/alert.service";
import {StepperFormManagementService} from "../../../core/services/stepper-form-management.service";

@Component({
  selector: 'app-slo-creation',
  templateUrl: './slo-creation.component.html',
  styleUrls: ['./slo-creation.component.css']
})
export class SloCreationComponent implements OnInit {

  sloTypes = ['Average Response Time', 'Throughput', 'Uptime'];
  selected: string;

  constructor(private formService: StepperFormManagementService) {
  }

  ngOnInit() {
  }
}
