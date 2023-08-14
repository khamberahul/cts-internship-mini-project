import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/user';

@Component({
  selector: 'app-edit-user-profile',
  templateUrl: './edit-user-profile.component.html',
  styleUrls: ['./edit-user-profile.component.css']
})
export class EditUserProfileComponent {
  users: any;
  data!: string;
  auth1!: string;
  use: any = "Hllo";
  user1: User = new User;
  d1: any;
  cy: any;
  by: any;
  age: any;
  public dob!: Date;
  constructor(private userService: UserService, private authService: AuthService, private router: Router, private toast: ToastrService) { }
  ngOnInit(): void {
    this.userService.getUsers().subscribe((profile) => {
      this.users = profile;
      // this.user1 = profile;
    })
  }
  onSubmit(employee: User) {

    const datepipe: DatePipe = new DatePipe('en-IN');
    this.d1 = datepipe.transform(new Date(this.users.dob), 'YYYY-dd-MM');
    this.cy = new Date().getFullYear();
    this.by = datepipe.transform(new Date(this.users.dob), 'YYYY');
    this.age = Math.abs(this.cy - this.by);
    const date = (this.d1).toLocaleString("en-US")
    this.user1.fullName = this.users.fullName;
    this.user1.aadharNumber = this.users.aadharNumber;
    this.user1.dob = this.d1;
    this.user1.age = this.age;
    this.user1.gender = this.users.gender;
    this.user1.phoneNumber = this.users.phoneNumber;
    this.user1.address = this.users.address;
    this.user1.userEmail = this.users.userEmail;
    this.user1.username = this.users.username;
    this.user1.userPassword = this.users.userPassword;
    this.user1.userType = "user";

    this.userService.updateProfile(this.user1, this.users?.userId).subscribe((data) => {
      this.toast.success("Data Updated")
    },
      (error) => {
        this.toast.warning("Something Went wrong")
        console.warn('oops', error)
        alert("Data Not Updated")
      }
    )
  }

  onChange(e: any) {
    this.users.gender = e.target.value;

  }
}
