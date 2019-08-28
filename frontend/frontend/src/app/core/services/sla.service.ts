import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Config} from "../../config";
import {SlaWithCustomer} from "../../shared/models/slaWithCustomer";
import {AuthenticationService} from "./authentication.service";
import {Sla} from "../../shared/models/sla";
import {SlaUserService} from "./sla-user.service";
import {SlaAndParties} from "../../shared/models/slaAndParties";
import {first} from "rxjs/operators";
import {BehaviorSubject} from "rxjs";
import {Report} from "../../shared/models/Report";
import {DeploymentData} from "../../shared/models/deploymentData";
import {ActivationDeposit} from "../../shared/models/activationDeposit";

@Injectable({
  providedIn: 'root'
})
export class SlaService {
  httpOptions = {
    withCredentials: false
  };

  slaCount: BehaviorSubject<number>;

  constructor(private http: HttpClient,
              private config: Config,
              private authService: AuthenticationService) {
    this.slaCount = new BehaviorSubject(0);
    this.countNewSLAs();
  }

  createSla(slaWithCustomer: SlaWithCustomer) {
    return this.http.post<Sla>(`${this.config.apiUrl}/slas`, slaWithCustomer, this.httpOptions)
  }

  getMySlas() {
    let userId = this.authService.currentUserValue.id as unknown as string;
    let params = new HttpParams();
    params = params.append('id', userId);
    return this.http.get<SlaAndParties[]>(`${this.config.apiUrl}/slas`, {params: params});
  }

  getSlasForReview() {
    let userId = this.authService.currentUserValue.id as unknown as string;
    let params = new HttpParams();
    params = params.append('id', userId);
    return this.http.get<SlaAndParties[]>(`${this.config.apiUrl}/slas/review`, {params: params});
  }

  getSlaWithParties(id) {
    return this.http.get<SlaAndParties>(`${this.config.apiUrl}/slas/${id}`)
  }

  updateSLAStatus(currentStatus: string, id: number) {
    return this.http.put<SlaAndParties>(`${this.config.apiUrl}/slas/${id}`, currentStatus);
  }

  deploy(sla: Sla) {
    let data = new DeploymentData();
    data.slaId = sla.id;
    data.serviceProviderId = sla.serviceProviderId;
    return this.http.post<Boolean>(`${this.config.apiUrl}/deploy`, data);
  }

  terminate(slaId: number) {
    return this.http.delete(`${this.config.apiUrl}/terminate/${slaId}`);
  }

  activateSLA(sla: SlaAndParties) {
    let deposit = new ActivationDeposit();
    deposit.customerId = sla.serviceCustomer.id;
    deposit.slaId = sla.id;
    deposit.slaPrice = sla.servicePrice;
    return this.http.put<Boolean>(`${this.config.apiUrl}/deposit`, deposit);
  }

  countNewSLAs() {
    if (this.authService.isLoggedIn()) {
      this.authService.currentUser.subscribe(user => {
        let userId = user.id as unknown as string;
        let params = new HttpParams();
        params = params.append('id', userId);
        this.http.get<number>(`${this.config.apiUrl}/slas/new`, {params: params}).pipe(first())
          .subscribe(count => {
            this.slaCount.next(count);
          });
      });
    }
  }

  getReport() {
    let params = this.getUserParams();
    return this.http.get<Report>(`${this.config.apiUrl}/slas/report`, {params: params})
  }

  private getUserParams() {
    let userId = this.authService.currentUserValue.id as unknown as string;
    let params = new HttpParams();
    params = params.append('id', userId);
    return params
  }
}
