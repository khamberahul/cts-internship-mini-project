import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/user';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  userName: string = localStorage.getItem('user_name') ?? "";
  logged: boolean = false;
  isHover: boolean = false;
  myProfileExpanded: boolean = false;
  isDrawerOpen: boolean = false;
  currentUser: any;
  navbarCollapsed: boolean = true;
  userType: string = localStorage.getItem('user_type') ?? "";


  constructor(private route: Router, private auth: AuthService) {
  }

  ngOnInit(): void {
    this.route.events.subscribe((routeData: any) => {

      if (routeData.url) {
        if (localStorage.getItem('cvs_token')) {
          this.currentUser = localStorage.getItem('user_name')
          this.logged = true;
        } else {
          this.logged = false;
        }
      }
    })
  }

  logout(): void {
    localStorage.clear();
    this.route.navigate(['/login']);
  }

  triggerHover() {
    this.isHover = !this.isHover;
  }

  toggleMyProfile() {
    this.myProfileExpanded = !this.myProfileExpanded;
  }

  toggleDrawerOpen() {
    this.isDrawerOpen = !this.isDrawerOpen;
  }

  toggleNavbarCollapsing() {
    this.navbarCollapsed = !this.navbarCollapsed;
  }

  handleHomeClick() {
    const _userType = localStorage.getItem('user_type');

    if (_userType === 'user') {
      this.route.navigate(['/user']);
    } else if (_userType === 'admin') {
      this.route.navigate(['/admin']);
    }
  }
}
