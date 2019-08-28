import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Config} from "../../config";
import {Ganache} from "../../shared/models/ganache";

@Injectable({
  providedIn: 'root'
})
export class GanacheService {

  constructor(private http: HttpClient,
              private config: Config) { }

  getUrl() {
    return this.http.get<Ganache>(`${this.config.apiUrl}/users/ganache`);
  }

  updateUrl(ganache: Ganache) {
    return this.http.put(`${this.config.apiUrl}/users/ganache`, ganache);
  }
}
