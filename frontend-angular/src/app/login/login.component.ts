import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserCredentials } from 'src/UserCredentials';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  errorStatus: any;
  userName: any;
  password: any;
  user: UserCredentials = new UserCredentials;
  passwordVisible: boolean = false;
  // logged: boolean = false;

  constructor(private authService: AuthService, private router: Router, private toastr: ToastrService) { }

  onSubmit(userCredentials: UserCredentials) {
    this.user = userCredentials;

    this.authService.login(this.user).subscribe((token) => {
      if (!token) {
        alert("Invalid User Credentials")
        this.errorStatus = "Invalid User Credentials"
      } else {

        // Decode token 
        // Set token and user_type as localStorage

        localStorage.setItem('cvs_token', token);
        localStorage.setItem('user_name', this.user.userName)
        this.authService.logged = true;

        this.authService.validate(token).subscribe((data1) => {

          if (data1) {
            this.authService.getUserType(token).subscribe((role) => {

              localStorage.setItem('user_type', role)
              if (role === "admin") {
                this.router.navigate(["/admin"])
              } else if (role === "user") {
                this.router.navigate(["/user"])
              } else {
                this.router.navigate(["/error-page"])
              }

            })
          } else {
            this.toastr.warning('Invalid Credentials', `Hey, ${userCredentials.userName}!`)
            console.warn("Invalid Token")
          }


        },
          (error) => {
            if (error) {
              this.toastr.warning('Login Unsuccessful', `Hey, ${userCredentials.userName}!`)
            }
          })
      }

    },
      (error) => {
        if (error) {
          (error.status)
          this.toastr.warning('Login Unsuccessful', `Hey, ${userCredentials.userName}!`)
        }
      })
  }

  passwordVisibilityToggle() {
    this.passwordVisible = !this.passwordVisible;
  }

}
