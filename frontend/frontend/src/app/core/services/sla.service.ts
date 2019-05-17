import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Config} from "../../config";
import {SlaWithCustomer} from "../../shared/models/slaWithCustomer";
import {AuthenticationService} from "./authentication.service";
import {Sla} from "../../shared/models/sla";

@Injectable({
  providedIn: 'root'
})
export class SlaService {
  httpOptions = {
    withCredentials: false
  };

  constructor(private http: HttpClient,
              private config: Config,
              private authService: AuthenticationService) { }

  createSla(slaWithCustomer: SlaWithCustomer) {
    return this.http.post(`${this.config.apiUrl}/sla/create`, slaWithCustomer, this.httpOptions)
  }

  getMySlas() {
    let params = new HttpParams();
    params = params.append('user', this.authService.currentUserValue.username);
    return this.http.get<Sla[]>(`${this.config.apiUrl}/sla/all`, {params: params});
  }
}
