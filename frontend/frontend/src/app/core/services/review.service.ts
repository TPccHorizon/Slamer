import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Config} from "../../config";
import {Review} from "../../shared/models/review";
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient,
              private config: Config,
              private authService: AuthenticationService) { }

  createReview(review: Review) {
    return this.http.post<Boolean>(`${this.config.apiUrl}/reviews`, review);
  }

  getReview(slaId: number) {
    return this.http.get<Review>(`${this.config.apiUrl}/reviews/${slaId}`);
  }

  private getUserParams() {
    let userId = this.authService.currentUserValue.id as unknown as string;
    let params = new HttpParams();
    params = params.append('id', userId);
    return params
  }

}
