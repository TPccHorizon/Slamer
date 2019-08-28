import {Component, Input, OnInit} from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {SlaService} from "../services/sla.service";
import {BalanceService} from "../services/balance.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  @Input() themeColor = '';
  newSlas: number;

  constructor(public authService: AuthenticationService,
              private slaService: SlaService,
              private balanceService: BalanceService,
              private route: Router) { }

  ngOnInit() {
    this.slaService.slaCount.subscribe(count => {
      this.newSlas = count;
    });
    if (this.authService.isLoggedIn()) {
      this.balanceService.getBalance();
    }
  }

  toSettings() {
    this.route.navigate(['/settings']);
  }

}
