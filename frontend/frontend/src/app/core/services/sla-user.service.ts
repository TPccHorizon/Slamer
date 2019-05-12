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

  getAll() {
    return this.http.get<SlaUser[]>(`${this.config.apiUrl}/users`, this.httpOptions)
  }

  getById(id: number) {
    return this.http.get<SlaUser>(`${this.config.apiUrl}/users/${id}`, this.httpOptions)
  }

  register(user: SlaUser) {
    return this.http.post(`${this.config.apiUrl}/users/register`, user, this.httpOptions);
  }

  update(user: SlaUser) {
    return this.http.put(`${this.config.apiUrl}/users/${user.id}`, user, this.httpOptions);
  }

  delete(id: number) {
    return this.http.delete(`${this.config.apiUrl}/users/${id}`, this.httpOptions);
  }
}
