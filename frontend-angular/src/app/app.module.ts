import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { ViewUserComponent } from './view-users/view-users.component';
import { LoginComponent } from './login/login.component';
import { PageErrorComponent } from './page-error/page-error.component';
import { HeaderComponent } from './header/header.component';
import { CvsFooterComponent } from './cvs-footer/cvs-footer.component';
import { ViewUsersPipe } from './view-users/view-users.pipe';
import { AboutUsComponent } from './about-us/about-us.component';
import { LandingUserComponent } from './landing-user/landing-user.component';
import { EditUserProfileComponent } from './edit-profile/edit-user-profile/edit-user-profile.component';
import { UpdateVaccineStatusComponent } from './update-vaccine-status/update-vaccine-status.component';
import { AddVaccineCenterComponent } from './vaccine-center/add-vaccine-center/add-vaccine-center.component';
import { LandingAdminComponent } from './landing-admin/landing-admin.component';
import { ViewCentersComponent } from './view-centers/view-centers.component';
import { CentersPipe } from './view-centers/centers.pipe';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    RegisterUserComponent,
    ViewUserComponent,
    LoginComponent,
    PageErrorComponent,
    HeaderComponent,
    CvsFooterComponent,
    ViewUsersPipe,
    AboutUsComponent,
    LandingUserComponent,
    EditUserProfileComponent,
    UpdateVaccineStatusComponent,
    AddVaccineCenterComponent,
    LandingAdminComponent,
    ViewCentersComponent,
    CentersPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ToastrModule.forRoot({
      toastClass:'ngx-toastr',
      tapToDismiss	:true,
      positionClass: 'toast-top-right',
      preventDuplicates:true,
      easing:'ease-in',
      timeOut:2500,
      progressBar:true,
      progressAnimation:'decreasing'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
