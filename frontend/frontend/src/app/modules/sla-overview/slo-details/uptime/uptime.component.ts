import {Component, Input, OnInit} from '@angular/core';
import {Uptime} from "../../../../shared/models/uptime";

@Component({
  selector: 'app-uptime',
  templateUrl: './uptime.component.html',
  styleUrls: ['./uptime.component.css']
})
export class UptimeComponent implements OnInit {

  @Input()
  uptime: Uptime;

  constructor() { }

  ngOnInit() {
  }

}
