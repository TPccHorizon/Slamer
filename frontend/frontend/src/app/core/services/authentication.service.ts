import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {SlaUser} from "../../shared/models/slaUser";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Config} from "../../config";
import {LoginData} from "../../shared/models/loginData";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  httpOptions = {
    withCredentials: false
  };

  private currentUserSubject: BehaviorSubject<SlaUser>;
  public currentUser: Observable<SlaUser>;

  constructor(private http: HttpClient, private config: Config, private router: Router) {
    this.currentUserSubject = new BehaviorSubject<SlaUser>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): SlaUser {
    return this.currentUserSubject.value;
  }

  login(loginData: LoginData) {
    return this.http.post<any>(`${this.config.apiUrl}/users/login`, loginData, this.httpOptions);
  }

  storeJwtToken(loginData: LoginData, token) {
    let currentUser = new SlaUser();
    currentUser.token = token;
    currentUser.username = loginData.username;
    localStorage.setItem('currentUser', JSON.stringify(currentUser));
    this.currentUserSubject.next(currentUser);
  }

  logout(){
    // remove user from local storage
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.router.navigate(['/']);
  }

  isLoggedIn() {
    return localStorage.getItem('currentUser');
  }


}
