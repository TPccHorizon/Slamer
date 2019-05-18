import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class StepperFormManagementService {

  private slaFormSource: Subject<FormGroup> = new Subject();
  slaForm : Observable<FormGroup> = this.slaFormSource.asObservable();

  private sloFormSource: Subject<FormGroup> = new Subject();
  sloForm: Observable<FormGroup> = this.sloFormSource.asObservable();

  constructor() { }

  slaFormReady(form: FormGroup) {
    this.slaFormSource.next(form);
  }

  sloFormReady(form: FormGroup){
    this.sloFormSource.next(form);
  }
}
