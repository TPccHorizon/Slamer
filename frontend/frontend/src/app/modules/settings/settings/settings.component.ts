import { Component, OnInit } from '@angular/core';
import {SlaUser} from "../../../shared/models/slaUser";
import {AuthenticationService} from "../../../core/services/authentication.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {SlaUserService} from "../../../core/services/sla-user.service";
import {AlertService} from "../../../core/services/alert.service";
import {BalanceService} from "../../../core/services/balance.service";
import {GanacheService} from "../../../core/services/ganache.service";
import {Ganache} from "../../../shared/models/ganache";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  user: SlaUser;
  isReadOnlyWallet = true;
  formGroup: FormGroup;

  ipFormGroup: FormGroup;
  ganacheUrl: Ganache;
  isReadOnlyGanache = true;

  constructor(private authService: AuthenticationService,
              private formBuilder: FormBuilder,
              private slaUserService: SlaUserService,
              private alertService: AlertService,
              private balanceService: BalanceService,
              private ganacheService: GanacheService) { }

  ngOnInit() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.authService.getMe(currentUser.username).subscribe(user => {
      this.user = user;
      this.formGroup = this.formBuilder.group({
        wallet: [user.wallet],
        privateKey: [user.privateKey]
      });

    });
    this.ganacheService.getUrl().subscribe(ganache => {
      this.ganacheUrl = ganache;
      let url = '';
      if (ganache && ganache.url) {
        url = ganache.url;
      }
      this.ipFormGroup = this.formBuilder.group({
        ipAddress: [url]
      })
    });
  }

  edit() {
    this.isReadOnlyWallet = !this.isReadOnlyWallet;
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
    this.isReadOnlyWallet = true;

  }

  editUrl() {
    this.isReadOnlyGanache = !this.isReadOnlyGanache;
  }

  saveUrl() {
    let oldUrl;
    if (this.ganacheUrl) {
      oldUrl = this.ganacheUrl.url;
    } else {
      oldUrl = '';
      this.ganacheUrl = new Ganache();
    }
    this.ganacheUrl.url = this.ipFormGroup.controls.ipAddress.value;
    this.ganacheService.updateUrl(this.ganacheUrl).subscribe(success => {
      this.alertService.success("Changed Ganache Url");
    }, error => {
      this.ganacheUrl.url = oldUrl;
      this.alertService.error(error);
    });
    this.isReadOnlyGanache = true;
  }

}
