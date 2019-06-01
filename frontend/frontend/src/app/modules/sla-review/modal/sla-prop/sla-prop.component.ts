import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-sla-prop',
  templateUrl: './sla-prop.component.html',
  styleUrls: ['./sla-prop.component.css']
})
export class SlaPropComponent implements OnInit {

  @Input()
  property: any;

  @Input()
  propertyName: string;

  constructor() { }

  ngOnInit() {
  }

}
