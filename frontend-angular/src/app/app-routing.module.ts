import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PageErrorComponent } from './page-error/page-error.component';
import { LandingUserComponent } from './landing-user/landing-user.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { ViewUserComponent } from './view-users/view-users.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { LoginGuard } from './cvs-guard/login.guard';
import { EditUserProfileComponent } from './edit-profile/edit-user-profile/edit-user-profile.component';
import { UpdateVaccineStatusComponent } from './update-vaccine-status/update-vaccine-status.component';
import { AddVaccineCenterComponent } from './vaccine-center/add-vaccine-center/add-vaccine-center.component';
import { LandingAdminComponent } from './landing-admin/landing-admin.component';
import { ViewCentersComponent } from './view-centers/view-centers.component';

const routes: Routes = [
  { path: 'register', component: RegisterUserComponent, canActivate: [LoginGuard] },
  { path: 'login', component: LoginComponent, canActivate: [LoginGuard] },
  { path: 'error-page', component: PageErrorComponent },
  {
    path: 'user', children: [
      { path: 'edit', component: EditUserProfileComponent },
      { path: 'aboutus', component: AboutUsComponent },
      { path: '', component: LandingUserComponent },
    ],
  },
  {
    path: 'admin', children: [
      { path: 'users', component: ViewUserComponent },
      { path: 'vaccinestatus', component: UpdateVaccineStatusComponent },
      { path: 'centers', component: ViewCentersComponent },
      { path: 'addcenter', component: AddVaccineCenterComponent },
      { path: 'aboutus', component: AboutUsComponent },
      { path: '', component: LandingAdminComponent },
    ],
  },
  { path: 'aboutus', component: AboutUsComponent },
  { path: '', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
