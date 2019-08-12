import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SlaUser} from "../../shared/models/slaUser";
import {Config} from "../../config";

@Injectable({
  providedIn: 'root'
})
export class SlaUserService {
  httpOptions = {
    withCredentials: false
  };

  constructor(private http: HttpClient, private config: Config) { }

  getById(id: number) {
    return this.http.get<SlaUser>(`${this.config.apiUrl}/users/${id}`, this.httpOptions)
  }

  register(user: SlaUser) {
    console.log(user)
    return this.http.post(`${this.config.apiUrl}/users/register`, user, this.httpOptions);
  }

  update(user: SlaUser) {
    return this.http.put<Boolean>(`${this.config.apiUrl}/users/${user.id}`, user, this.httpOptions);
  }

  registerMonitoringService(user: SlaUser, slaId: number) {
    return this.http.post(`${this.config.apiUrl}/monitor/register/${slaId}`, user);
  }

  selectExistingMonitoringService(serviceId: number, slaId: number) {
    return this.http.post(`${this.config.apiUrl}/users/monitor/select/${slaId}`, serviceId);
  }

  getAllMonitoringServices() {
    return this.http.get<SlaUser[]>(`${this.config.apiUrl}/users/monitor`);
  }
}
