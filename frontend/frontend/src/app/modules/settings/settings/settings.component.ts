import { Component, OnInit } from '@angular/core';
import {SlaUser} from "../../../shared/models/slaUser";
import {AuthenticationService} from "../../../core/services/authentication.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {SlaUserService} from "../../../core/services/sla-user.service";
import {AlertService} from "../../../core/services/alert.service";
import {BalanceService} from "../../../core/services/balance.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  user: SlaUser;
  isReadOnly = true;
  formGroup: FormGroup;

  constructor(private authService: AuthenticationService,
              private formBuilder: FormBuilder,
              private slaUserService: SlaUserService,
              private alertService: AlertService,
              private balanceService: BalanceService) { }

  ngOnInit() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.authService.getMe(currentUser.username).subscribe(user => {
      this.user = user;
      this.formGroup = this.formBuilder.group({
        wallet: [user.wallet],
        privateKey: [user.privateKey]
      });
    });
  }

  edit() {
    this.isReadOnly = !this.isReadOnly;
  }

  save() {
    let oldKey = this.user.privateKey;
    let oldWallet = this.user.wallet;
    this.user.privateKey = this.formGroup.controls.privateKey.value;
    this.user.wallet = this.formGroup.controls.wallet.value;
    this.slaUserService.update(this.user).subscribe(success => {
      this.alertService.success("Changed Wallet credentials");
      this.balanceService.getBalance();
    }, error => {
      this.user.wallet = oldWallet;
      this.user.privateKey = oldKey;
      this.alertService.error(error);
    });
    this.isReadOnly = true;

  }

}
