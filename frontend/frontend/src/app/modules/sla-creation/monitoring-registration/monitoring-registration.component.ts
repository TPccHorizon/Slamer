import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../../core/services/authentication.service";
import {SlaUserService} from "../../../core/services/sla-user.service";
import {AlertService} from "../../../core/services/alert.service";
import {first} from "rxjs/operators";
import {SlaUser} from "../../../shared/models/slaUser";
import {pipe} from "rxjs";

@Component({
  selector: 'app-monitoring-registration',
  templateUrl: './monitoring-registration.component.html',
  styleUrls: ['./monitoring-registration.component.css']
})
export class MonitoringRegistrationComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
  takeExistingService = false;

  monitoringServices: SlaUser[] = [];
  selectedServiceId: number;

  currentSlaId = -1;


  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: SlaUserService,
              private alertService: AlertService,
              private route : ActivatedRoute

  ) {
    this.route.params.subscribe(param => {
      console.log(param['id']);
      this.currentSlaId = param['id'];
    });
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      partyName: ['', Validators.required],
      wallet: ['', Validators.required],
      privateKey: ['', Validators.required],
      password: ['', Validators.required],
    });
    this.userService.getAllMonitoringServices().pipe(first()).subscribe(res => {
      this.monitoringServices = res;
      console.log("got services");
    }, error => {
      this.alertService.error("No Monitoring Services found");
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      console.log("Invalid");
      return;
    }
    console.log("Valid");
    console.log(this.registerForm.value);

    this.loading = true;
    this.userService.registerMonitoringService(this.registerForm.value, this.currentSlaId)
      .pipe(first())
      .subscribe(data => {
        console.log("Success:");
        console.log(data);
        this.alertService.success('Monitoring Service registered', true);
        this.router.navigate(['/slas/' + this.currentSlaId + '/slo'])
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      })
  }

  registerExistingSolution() {
    this.userService.selectExistingMonitoringService(this.selectedServiceId, this.currentSlaId)
      .pipe(first()).subscribe(success => {
        this.alertService.success('Monitoring Service registered');
        this.router.navigate(['/slas/' + this.currentSlaId + '/slo']);
    }, error => {
        this.alertService.error('Could not register this service');
        console.log(error);
    });
  }

}
