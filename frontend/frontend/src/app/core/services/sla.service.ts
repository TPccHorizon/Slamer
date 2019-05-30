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
    let params = new HttpParams();
    params = params.append('user', this.authService.currentUserValue.username);
    return this.http.get<Sla[]>(`${this.config.apiUrl}/slas`, {params: params});
  }

  getSlasForReview() {
    let params = new HttpParams();
    params = params.append('id', this.authService.currentUserValue.id as unknown as string);
    return this.http.get(`${this.config.apiUrl}/slas/review`, {params: params});
  }

  getSlaWithParties(id) {
    return this.http.get<SlaAndParties>(`${this.config.apiUrl}/slas/${id}`)
  }

  updateSLAStatus(currentStatus: string, id: number) {
    return this.http.put<SlaAndParties>(`${this.config.apiUrl}/slas/${id}`, currentStatus);
  }

  countNewSLAs() {
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
