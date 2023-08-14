import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { UpdateUserCenter } from 'src/updateUserCenter';
import { User } from 'src/user';

const { usersBaseUrl, vaccineBaseUrl } = environment;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  viewVaccinationStatusUrl: string;
  authToken: string;
  viewStatusUrl: string;
  updateProfileUrl: string;
  constructor(private http: HttpClient) {
    this.viewStatusUrl = `${usersBaseUrl}/user/userstatus/`
    this.viewVaccinationStatusUrl = `${usersBaseUrl}/user/vaccinestatus/`
    this.updateProfileUrl = `${usersBaseUrl}/user/edit/`
    this.authToken = localStorage.getItem('cvs_token') ?? ""
  }

  viewStatus(id: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authToken}`,
    })

    return this.http.get(this.viewStatusUrl + id, { headers: headers, responseType: 'text' })
  }

  vaccinationStatus(id: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authToken}`,
    })

    return this.http.get(this.viewVaccinationStatusUrl + id, { headers: headers, responseType: 'text' })
  }

  getUsers() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authToken}`,
    })

    const userName = localStorage.getItem('user_name') ?? ""
    return this.http.get(`${usersBaseUrl}/user/user/` + userName, { headers: headers, responseType: 'json' })
  }

  updateProfile(user: User, userId: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authToken}`,
    })
    return this.http.put(`${this.updateProfileUrl}/${userId}`, user, { headers: headers, responseType: 'text' })
  }

  getCenters() {
    const getCentersUrl: string = `${vaccineBaseUrl}/vaccinecenter/center/`

    return this.http.get(getCentersUrl, { responseType: 'json' })

  }

  scheduleVaccination(user: UpdateUserCenter, userId: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authToken}`,
    })

    return this.http.put(`${this.updateProfileUrl}/${userId}`, user, { headers: headers, responseType: 'text' })
  }

  getCenterDetails(centerId: any) {
    const getCenterDetailsUrl = `${vaccineBaseUrl}/vaccinecenter/center/${centerId}`;

    return this.http.get(getCenterDetailsUrl);
  }
}
