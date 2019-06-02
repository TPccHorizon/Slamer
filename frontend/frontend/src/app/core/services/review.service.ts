import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Config} from "../../config";
import {Review} from "../../shared/models/review";

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient, private config: Config) { }

  createReview(review: Review) {
    return this.http.post<Boolean>(`${this.config.apiUrl}/reviews`, review)
  }

}
