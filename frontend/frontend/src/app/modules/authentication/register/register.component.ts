import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../core/services/authentication.service";
import {SlaUserService} from "../../../core/services/sla-user.service";
import {AlertService} from "../../../core/services/alert.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: SlaUserService,
              private alertService: AlertService
  ) {
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      partyName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
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
        this.alertService.success('Registration successful', true);
        this.router.navigate(['/login'])
      }, error => {
        this.alertService.error(error);
        this.loading = false;
      })
  }

}
