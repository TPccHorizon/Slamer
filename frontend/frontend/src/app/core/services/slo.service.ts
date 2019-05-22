import { Injectable } from '@angular/core';
import {ServiceLevelObjective} from "../../shared/models/serviceLevelObjective";
import {BehaviorSubject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Config} from "../../config";

@Injectable({
  providedIn: 'root'
})
export class SloService {

  private sloSource = new BehaviorSubject<ServiceLevelObjective[]>([]);
  private currentSlaId : number;

  private slos = this.sloSource.asObservable();


  constructor(private http: HttpClient, private config: Config) {
  }

  createSlo(slo: ServiceLevelObjective) {
    slo.slaId = this.getCurrentSlaId();
    return this.http.post<ServiceLevelObjective>(`${this.config.apiUrl}/slas/${this.currentSlaId}/slos`, slo)
  }

  addSlo(slo: ServiceLevelObjective) {
    let currentValue = this.sloSource.value;
    let updatedValue = [...currentValue, slo];
    this.sloSource.next(updatedValue);
  }

  getSlos(){
    return this.slos;
  }

  setCurrentSlaId(id: number) {
    this.currentSlaId = id;
  }

  getCurrentSlaId() {
    return this.currentSlaId;
  }


}
