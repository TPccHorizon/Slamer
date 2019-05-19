import {Component, Input, OnInit} from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  @Input() themeColor = '';

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
  }

}
