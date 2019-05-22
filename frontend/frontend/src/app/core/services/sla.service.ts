import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Config} from "../../config";
import {SlaWithCustomer} from "../../shared/models/slaWithCustomer";
import {AuthenticationService} from "./authentication.service";
import {Sla} from "../../shared/models/sla";
import {SlaUserService} from "./sla-user.service";
import {SlaAndParties} from "../../shared/models/slaAndParties";

@Injectable({
  providedIn: 'root'
})
export class SlaService {
  httpOptions = {
    withCredentials: false
  };

  constructor(private http: HttpClient,
              private config: Config,
              private authService: AuthenticationService,
              private userSevice: SlaUserService) { }

  createSla(slaWithCustomer: SlaWithCustomer) {
    return this.http.post<Sla>(`${this.config.apiUrl}/slas`, slaWithCustomer, this.httpOptions)
  }

  getMySlas() {
    let params = new HttpParams();
    params = params.append('user', this.authService.currentUserValue.username);
    return this.http.get<Sla[]>(`${this.config.apiUrl}/slas`, {params: params});
  }

  getSlaWithParties(id) {
    return this.http.get<SlaAndParties>(`${this.config.apiUrl}/slas/${id}`)
  }
}
