import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AlertService} from "../../../core/services/alert.service";

@Component({
  selector: 'app-sla-create',
  templateUrl: './sla-create.component.html',
  styleUrls: ['./sla-create.component.css']
})
export class SlaCreateComponent implements OnInit {

  slaForm: FormGroup;
  submitted = false;
  loading = false;

  constructor(private formBuilder: FormBuilder,
              private alertService: AlertService) { }

  ngOnInit() {
    this.slaForm = this.formBuilder.group({
      serviceCustomerUsername: ['', Validators.required],
      validFrom: ['', Validators.required],
      validTo: ['', Validators.required],
      servicePrice: ['', Validators.required]
    })
  }

  get f() { return this.slaForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.slaForm.invalid) {
      return;
    }
    this.loading = true;
    console.log("SLA valid");
  //  call sla service
    this.loading = false;
  }

}
