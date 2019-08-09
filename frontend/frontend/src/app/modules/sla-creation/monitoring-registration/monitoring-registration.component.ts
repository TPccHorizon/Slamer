import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../../../core/services/authentication.service";
import {SlaUserService} from "../../../core/services/sla-user.service";
import {AlertService} from "../../../core/services/alert.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-monitoring-registration',
  templateUrl: './monitoring-registration.component.html',
  styleUrls: ['./monitoring-registration.component.css']
})
export class MonitoringRegistrationComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;

  currentSlaId = -1;


  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: SlaUserService,
              private alertService: AlertService,
              private route : ActivatedRoute

  ) {
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
    this.route.params.subscribe(param => {
      console.log(param['id']);
      this.currentSlaId = param['id'];
    });
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      serviceName: ['', Validators.required],
      wallet: ['', Validators.required],
      privateKey: ['', Validators.required],
    })
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

    this.loading = true;
    this.userService.register(this.registerForm.value)
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

}
