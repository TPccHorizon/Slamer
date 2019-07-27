import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Config} from "../../config";

@Injectable({
  providedIn: 'root'
})
export class BalanceService {

  public currentBalance: string;

  constructor(private http: HttpClient, private config: Config) { }

  getBalance() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.http.get<string>(`${this.config.apiUrl}/balance/${currentUser.id}`).subscribe(bal => {
      this.currentBalance = bal;
    });
  }
}
