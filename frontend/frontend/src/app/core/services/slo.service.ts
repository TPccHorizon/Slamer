import { Injectable } from '@angular/core';
import {ServiceLevelObjective} from "../../shared/models/serviceLevelObjective";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SloService {

  private sloSource = new BehaviorSubject<ServiceLevelObjective[]>([]);

  private slos = this.sloSource.asObservable();

  addSlo(slo: ServiceLevelObjective) {
    let currentValue = this.sloSource.value;
    let updatedValue = [...currentValue, slo];
    this.sloSource.next(updatedValue);
  }

  getSlos(){
    return this.slos;
  }


}
