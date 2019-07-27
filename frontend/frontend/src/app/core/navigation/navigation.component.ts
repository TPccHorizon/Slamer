import {Component, Input, OnInit} from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {SlaService} from "../services/sla.service";
import {BalanceService} from "../services/balance.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  @Input() themeColor = '';
  newSlas: number;

  constructor(private authService: AuthenticationService,
              private slaService: SlaService,
              private balanceService: BalanceService) { }

  ngOnInit() {
    this.slaService.slaCount.subscribe(count => {
      this.newSlas = count;
    });
    this.balanceService.getBalance();
  }

}
