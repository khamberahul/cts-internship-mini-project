import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/user';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {
  fullName: any;
  aadharNumber: any;
  public dob!: Date;
  age: any;
  gender: any = "Female";
  phoneNumber: any;
  address: any;
  username: any;
  userEmail: any;
  userPassword: any;
  userType: any = "user";
  vaccinationDate: any = null;
  vaccinationStatus: any = "pending";
  userStatus: any = "pending";
  centerId: any = null;
  cy: any;
  by: any;
  pass!: string;
  public message: any
  headers1: any;
  data!: string;
  auth1!: string;
  use: any = "Hllo";
  user1: User = new User;
  d1: any;
  passwordVisible: boolean;

  constructor(private httpClient: HttpClient, private authService: AuthService, private router: Router) {
    this.passwordVisible = false;
  }

  ngOnInit(): void {
    // this.register.healthCheck().subscribe((data)=>{
    //   console.log(this.data)
    // },
    // (error)=>{
    //   this.router.navigate(["/error-page"])
    //   console.log(error)
    // }
    // )
  }
  onSubmit(employee: User) {

    const datepipe: DatePipe = new DatePipe('en-IN');
    this.d1 = datepipe.transform(new Date(this.dob), 'YYYY-dd-MM');
    this.cy = new Date().getFullYear();
    this.by = datepipe.transform(new Date(this.dob), 'YYYY');
    this.age = Math.abs(this.cy - this.by);
    const date = (this.d1).toLocaleString("en-US")
    this.user1.fullName = this.fullName;
    this.user1.aadharNumber = this.aadharNumber;
    this.user1.dob = this.d1;
    this.user1.age = this.age;
    this.user1.gender = this.gender;
    this.user1.phoneNumber = this.phoneNumber;
    this.user1.address = this.address;
    this.user1.userEmail = this.userEmail;
    this.user1.username = this.username;
    this.user1.userPassword = this.userPassword;
    this.user1.userType = "user";

    this.authService.registerUser(this.user1).subscribe((data) => {
      this.router.navigate(["/login"])
    },
      (error) => {
        console.warn('oops', error)
        alert("Please try with different username and Email")
        // this.router.navigate(["/error-page"])
      }
    )
  }
  onChange(e: any) {
    this.gender = e.target.value;

  }

  passwordVisibilityToggle() {
    this.passwordVisible = !this.passwordVisible;
  }

}
