import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {SlaCreateComponent} from "../sla-create/sla-create.component";
import {FormGroup} from "@angular/forms";
import {StepperFormManagementService} from "../../../core/services/stepper-form-management.service";

@Component({
  selector: 'app-sla-stepper',
  templateUrl: './sla-stepper.component.html',
  styleUrls: ['./sla-stepper.component.css']
})
export class SlaStepperComponent {


  slaForm: FormGroup;
  allFormsInitialized = false;

  constructor(private formService: StepperFormManagementService) {
    this.formService.slaForm.subscribe(form => this.slaForm = form);
  }

}
