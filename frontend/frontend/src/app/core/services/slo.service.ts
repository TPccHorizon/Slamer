import { Injectable } from '@angular/core';
import {ServiceLevelObjective} from "../../shared/models/serviceLevelObjective";
import {BehaviorSubject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Config} from "../../config";

@Injectable({
  providedIn: 'root'
})
export class SloService {

  public sloSource = new BehaviorSubject<ServiceLevelObjective[]>([]);
  private currentSlaId : number;

  public slos = this.sloSource.asObservable();


  constructor(private http: HttpClient, private config: Config) {
  }

  createSlo(slo: ServiceLevelObjective) {
    return this.http.post<ServiceLevelObjective>(`${this.config.apiUrl}/slas/${slo.slaId}/slos`, slo)
  }

  addSlo(slo: ServiceLevelObjective) {
    let currentValue = this.sloSource.value;
    let updatedValue = [...currentValue, slo];
    this.sloSource.next(updatedValue);
  }

  getSlos(id: number){
    return this.http.get<ServiceLevelObjective[]>(`${this.config.apiUrl}/slas/${id}/slos`);
  }

  setCurrentSlaId(id: number) {
    this.currentSlaId = id;
  }

  getCurrentSlaId() {
    return this.currentSlaId;
  }


}
