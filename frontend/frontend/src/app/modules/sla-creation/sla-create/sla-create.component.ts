import {Component, EventEmitter, HostListener, OnInit, Output, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AlertService} from "../../../core/services/alert.service";
import {SlaService} from "../../../core/services/sla.service";
import {SlaWithCustomer} from "../../../shared/models/slaWithCustomer";
import {Sla} from "../../../shared/models/sla";
import {AuthenticationService} from "../../../core/services/authentication.service";
import {first} from "rxjs/operators";
import {Router} from "@angular/router";
import {SloService} from "../../../core/services/slo.service";
import {NgbCalendar, NgbDate, NgbDateStruct, NgbInputDatepicker} from "@ng-bootstrap/ng-bootstrap";
import * as moment from 'moment';

@Component({
  selector: 'app-sla-create',
  templateUrl: './sla-create.component.html',
  styleUrls: ['./sla-create.component.scss']
})
export class SlaCreateComponent implements OnInit {

  slaForm: FormGroup;

  @ViewChild('dp') private datePicker: NgbInputDatepicker;
  hoveredDate: Date;
  dateRangeSelection = { from: Date, to: Date };
  from: Date;
  to: Date;
  isOpen = false;

  @HostListener('document:click', ['$event.target']) onClick(element) {
    const host = document.getElementById('dateRangePicker');
    if (this.datePicker && this.isOpen && !this.isDescendant(host, element)) {
      this.emit(true);
    }
  }

  submitted = false;
  loading = false;

  constructor(private formBuilder: FormBuilder,
              private alertService: AlertService,
              private slaService: SlaService,
              private authService: AuthenticationService,
              private sloService: SloService,
              private router: Router) {
    this.slaForm = this.formBuilder.group({
      serviceCustomerUsername: ['', Validators.required],
      title: ['', Validators.required],
      servicePrice: ['', Validators.required]
    });

  }

  ngOnInit() {

  }

  get f() { return this.slaForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.slaForm.invalid) {
      return;
    }
    this.loading = true;
    console.log("SLA valid");
    let slaWithCustomer = this.mapToSlaWithCustomer();
    this.slaService.createSla(slaWithCustomer)
      .pipe(first())
      .subscribe(sla => {
        console.log("Created new Sla.");
        this.alertService.success('Created new SLA', true);
        console.log(sla);
        this.sloService.setCurrentSlaId(sla.id);
        this.router.navigate(['/slas/' + sla.id + '/slo']);
      }, error => {
        this.alertService.error('Customer E-Mail not found');
        this.loading = false;
      });

    this.loading = false;
  }

  mapToSlaWithCustomer() {
    let sla = new Sla();
    let slaWithCustomer = new SlaWithCustomer();
    let providerUsername = this.authService.currentUserValue.username;
    console.log("User id provider: " +this.authService.currentUserValue.username);
    sla.validFrom = this.from;
    sla.validTo = this.to;
    sla.servicePrice = this.f.servicePrice.value;
    sla.title = this.f.title.value;
    slaWithCustomer.sla = sla;
    slaWithCustomer.customerUsername = this.f.serviceCustomerUsername.value;
    slaWithCustomer.providerUsername = providerUsername;
    return slaWithCustomer;
  }


  /***************************
   * Functions for Daterange selection
   *
   *
   * */

  private emit(close?: boolean) {

    if (close) {
      this.isOpen = false;
      this.datePicker.close();
    }
  }

  private isDescendant(parent, child) {
    let node = child;
    while (node !== null) {
      if (node === parent) {
        return true;
      } else {
        node = node.parentNode;
      }
    }
    return false;
  }

  onDateSelection(date: NgbDateStruct) {
    if (!this.from && !this.to) {
      this.from = this.toDate(date);
    } else if (this.from && !this.to && this.toMoment(date).isAfter(this.from)) {
      this.to = this.toDate(date);
      this.emit(true);
    } else {
      this.to = null;
      this.from = this.toDate(date);
    }
  }

  isHovered = (date: NgbDateStruct) => this.from && !this.to && this.hoveredDate
    && this.toMoment(date).isAfter(this.from) && this.toMoment(date).isBefore(this.hoveredDate);

  isInside = (date: NgbDateStruct) => this.toMoment(date).isAfter(moment(this.from).startOf('day')) && this.toMoment(date).isBefore(moment(this.to).startOf('day'));
  isFrom = (date: NgbDateStruct) => this.toMoment(date).isSame(this.from, 'd');
  isTo = (date: NgbDateStruct) => this.toMoment(date).isSame(this.to, 'd');


  toDate(dateStruct: NgbDateStruct): Date {
    return dateStruct ? new Date(dateStruct.year, dateStruct.month - 1, dateStruct.day) : null;
  }

  toMoment(dateStruct: NgbDateStruct): moment.Moment {
    return moment(this.toDate(dateStruct));
  }

  get formattedDateRange(): string {
    if (!this.from) {
      return `Click to select a date range`;
    }

    const fromFormatted = moment(this.from).format('DD.MM.YYYY');

    return this.to
      ? `${fromFormatted}`
      + ` - `
      + `${moment(this.to).format('DD.MM.YYYY')}`
      : `${fromFormatted}`;

  }

}
