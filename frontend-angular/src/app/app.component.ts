import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginComponent } from './login/login.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'covid-vaccination-portal';
  user_type = localStorage.getItem('user_type');

  constructor(private route: Router) { }

  ngOnInit() {
    // Fetch user_type and set in localStorage 
    // admin -> ['/login', '/users']

    if (localStorage.getItem('user_type') === 'admin') {
      this.route.navigate(['/admin/'])
    } else if (localStorage.getItem('user_type') === 'user') {
      this.route.navigate(['/user'])
    }
  }
}
