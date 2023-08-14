import { Component } from '@angular/core';
import { formatDate, DatePipe } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

import { UpdateUserCenter } from 'src/updateUserCenter';
import { AuthService } from '../service/auth.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-landing-user',
  templateUrl: './landing-user.component.html',
  styleUrls: ['./landing-user.component.css']
})
export class LandingUserComponent {
  approval: any;
  vaccinationStatus: any;
  centers: any = [];
  vaccines: any = [];
  updateCenter: any = new UpdateUserCenter();
  user: any = {};

  userCenter: any;
  userVaccine: any;

  selectedCenterId: any;
  selectedCenterValue: string = 'Select a center';
  selectedVaccineId: any;
  selectedVaccineValue: string = 'Select a vaccine type';

  centerClass: string = 'schedule-warning';
  vaccineClass: string = 'schedule-warning';

  displayVaccinationAppointment: boolean = false;

  // how many days are left
  daysLeft: number = 0;

  // functions
  formatDate = formatDate;

  constructor(private userService: UserService, private authService: AuthService, private toast: ToastrService) {


  }

  ngOnInit(): void {
    this.authService.checkState()


    this.userService.getUsers().subscribe((profile) => {
      this.user = profile;
      this.updateCenter = profile;
      this.displayVaccinationAppointment = this.user.centerId !== null;

      this.userService.viewStatus(this.user?.userId).subscribe((data) => {
        this.approval = data;
      })

      this.userService.vaccinationStatus(this.user?.userId).subscribe((data) => {
        this.vaccinationStatus = data;
      })

      this.fetchCenterDetails();

      // this.daysLeft = new Date(this.user?.vaccinationDate);
      // this.daysLeft.setDate(this.daysLeft.getDate() - new Date().getDate());

      const currentDate: Date = new Date();
      const dateSent: Date = new Date(this.user?.vaccinationDate)

      this.daysLeft = Math.abs(Math.floor((Date.UTC(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()) - Date.UTC(dateSent.getFullYear(), dateSent.getMonth(), dateSent.getDate())) / (1000 * 60 * 60 * 24)))

    })

    this.populateDropdown();

  }

  populateDropdown() {
    this.userService.getCenters().subscribe((_centers: any) => {
      this.centers = _centers;

    })
  }

  fetchCenterDetails() {
    this.userService.getCenterDetails(this.user?.centerId).subscribe((data) => {
      this.userCenter = data;
      this.userVaccine = this.userCenter?.detailsList?.filter((vaccine: any) => vaccine?.vaccineId.toString() === this.user?.vaccineId.toString())[0];
    })
  }

  onCenterSelect(_id: string, _value: string) {
    this.selectedCenterId = _id
    this.selectedCenterValue = _value;

    this.vaccines = this.centers.filter((c: any) => c?.centerId === _id)[0]?.detailsList;

    if (this.vaccines.length === 0) {
      this.selectedCenterId = undefined;
      this.selectedCenterId = 'Select a center'
      this.selectedVaccineId = undefined;
      this.selectedVaccineValue = 'Select a vaccine type'

      this.centerClass = 'schedule-warning';

      this.toast.warning('', 'No vaccines available currently at this center.')
    } else {
      this.centerClass = 'schedule-success';
    }

  }

  onVaccineSelect(_id: string, _value: string) {
    this.selectedVaccineId = _id;
    this.selectedVaccineValue = _value;
  }

  onScheduleVaccination() {
    if (this.selectedCenterId !== undefined && this.selectedVaccineId !== undefined) {

      this.updateCenter.centerId = this.selectedCenterId;
      this.updateCenter.vaccineId = this.selectedVaccineId;

      this.userService.scheduleVaccination(this.updateCenter, this.user?.userId).subscribe((data) => {

        this.userCenter = this.centers.filter((center: any) => center.centerId === this.selectedCenterId)[0];

        this.displayVaccinationAppointment = true;

        this.toast.success('', 'Appointment scheduled successfully!')
      }, (error) => {
        this.toast.warning('', `There was a problem while booking an appointment.`)
      })

    } else if (this.selectedCenterId === undefined) {
      this.toast.warning('', `Please select a center.`);
    } else if (this.selectedVaccineId === undefined) {
      this.toast.warning('', `Please select a vaccine type.`);
    }

    this.fetchCenterDetails();


  }

}

