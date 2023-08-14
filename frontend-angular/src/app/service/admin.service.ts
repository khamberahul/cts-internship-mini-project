import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs/internal/Observable';


const { usersBaseUrl, vaccineBaseUrl } = environment;

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  getAllUsersUrl: string;
  authToken: string;

  constructor(private http: HttpClient) {
    this.getAllUsersUrl = `${usersBaseUrl}/user/`;
    this.authToken = localStorage.getItem('cvs_token') ?? "";
  }

  getUsers(token: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`,
    })
    return this.http.get(this.getAllUsersUrl, { headers: headers, responseType: 'json' })
  }

  changeUserStatus(userId: string, token: string, status: string) {
    const changeStatusUrl: string = `${usersBaseUrl}/user/userstatus/${userId}/${status.toLowerCase() === 'reject' ? 'Reject' : 'Accept'}/`

    const headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
      'Authorization': `Bearer ${token}`,
    })

    const response = this.http.put(changeStatusUrl, null, { headers: headers, responseType: 'text' })
    return response;
  }

  changeVaccinationStatus(userId: string, token: string, status: string) {
    const changeVaccinationStatusUrl: string = `${usersBaseUrl}/user/vaccinationstatus/${userId}/${status}/`

    const headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
      'Authorization': `Bearer ${token}`,
    })

    const response = this.http.put(changeVaccinationStatusUrl, null, { headers: headers, responseType: 'text' })

    return response;
  }

  getCenters() {
    const getCentersUrl: string = `${vaccineBaseUrl}/vaccinecenter/center`

    return this.http.get(getCentersUrl, { responseType: 'json' })

  }

  addCenter(centerDetails: any): Observable<{}> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authToken}`,
    })
    const addCentersUrl: string = `${vaccineBaseUrl}/vaccinecenter/center`
    return this.http.post(addCentersUrl, centerDetails, { headers: headers });

  }

  deleteCenter(id: number) {
    const deleteCenterUrl: string = `${vaccineBaseUrl}/vaccinecenter/center/delete/${id}`

    const headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
      'Authorization': `Bearer ${this.authToken}`,
    })
    return this.http.delete(deleteCenterUrl, { headers, responseType: 'text' });
  }

  editCenter(id: string, _center: any) {
    const editCenterUrl: string = `${vaccineBaseUrl}/vaccinecenter/center/edit/${id}/`;

    const headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
      'Authorization': `Bearer ${this.authToken}`,
    })

    return this.http.put(editCenterUrl, _center, { headers: headers, responseType: 'text' });
  }

  deleteVaccineDetail(id: string) {
    const deleteVaccineDetailUrl: string = `${vaccineBaseUrl}/vaccinecenter/vaccine/${id}/`

    const headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
      'Authorization': `Bearer ${this.authToken}`,
    })

    return this.http.delete(deleteVaccineDetailUrl, { headers, responseType: 'text' });


  }
}
