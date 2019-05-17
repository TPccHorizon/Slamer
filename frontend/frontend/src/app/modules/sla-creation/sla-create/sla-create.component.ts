import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AlertService} from "../../../core/services/alert.service";
import {SlaService} from "../../../core/services/sla.service";
import {SlaWithCustomer} from "../../../shared/models/slaWithCustomer";
import {Sla} from "../../../shared/models/sla";
import {AuthenticationService} from "../../../core/services/authentication.service";
import {first} from "rxjs/operators";

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
              private alertService: AlertService,
              private slaService: SlaService,
              private authService: AuthenticationService) { }

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
    let slaWithCustomer = this.mapToSlaWithCustomer();
    this.slaService.createSla(slaWithCustomer)
      .pipe(first())
      .subscribe(data => {
        console.log("Created new Sla.");
        this.alertService.success('SLA Creation Successful', true);
        console.log(data);
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      });

    this.loading = false;
  }

  mapToSlaWithCustomer() {
    let sla = new Sla();
    let slaWithCustomer = new SlaWithCustomer();
    let providerUsername = this.authService.currentUserValue.username;
    console.log("User id provider: " +this.authService.currentUserValue.username);
    sla.validFrom = this.f.validFrom.value;
    sla.validTo = this.f.validTo.value;
    sla.servicePrice = this.f.servicePrice.value;
    slaWithCustomer.sla = sla;
    slaWithCustomer.customerUsername = this.f.serviceCustomerUsername.value;
    slaWithCustomer.providerUsername = providerUsername;
    return slaWithCustomer;
  }

}
