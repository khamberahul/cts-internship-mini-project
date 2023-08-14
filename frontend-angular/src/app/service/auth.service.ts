import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';

import { UserCredentials } from 'src/UserCredentials';
import { User } from 'src/user';
import { environment } from 'src/environments/environment';

const { authBaseUrl, usersBaseUrl } = environment;


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loginUrl: string
  authToken: string;
  validateUrl: string;
  userTypeUrl: string;
  serverHealthCheckUrl: string;
  logged: boolean = false;

  constructor(private http: HttpClient, private route: Router) {
    this.loginUrl = `${authBaseUrl}/auth/login/`
    this.validateUrl = `${authBaseUrl}/auth/validate/`
    this.userTypeUrl = `${authBaseUrl}/auth/users/userType/`
    this.serverHealthCheckUrl = `${usersBaseUrl}/user/health-check`
    this.authToken = localStorage.getItem('cvs_token') ?? '';
  }

  login(user: UserCredentials): Observable<any> {
    return this.http.post(this.loginUrl, user, { responseType: 'text' });
  }

  registerUser(user: User): Observable<any> {
    return this.http.post(`${usersBaseUrl}/user/`, user, { observe: 'response', responseType: 'text' }).pipe(catchError(this.handleError));
    // added / after add
  }

  getUserType(token: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    })

    return this.http.get(this.userTypeUrl, { headers: headers, responseType: 'text' })

  }
  validate(token: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    })

    return this.http.get(this.validateUrl, { headers: headers, responseType: 'json' })

  }

  healthCheck(): Observable<any> {
    return this.http.get(this.serverHealthCheckUrl, { responseType: 'text' });//.pipe(catchError(this.handleError));
  }

  handleError(error: any) {
    return throwError(error.message || "Server Not Found")
  }
  checkState() {
    const type = localStorage.getItem('user_type')
    if (type == null) {
      this.route.navigate(['login'])
    }
    if (type == "user") {
      this.route.navigate(['landing'])
    }
    if (type == "admin") {
      this.route.navigate(['users', 'vaccinestatus'])
    }
  }
}
