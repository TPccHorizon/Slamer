import { Component, OnInit } from '@angular/core';
import {SlaUser} from "../../../shared/models/slaUser";
import {AuthenticationService} from "../../../core/services/authentication.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  user: SlaUser;

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.authService.getMe(currentUser.username).subscribe(user => {
      this.user = user;
    });
  }

}
