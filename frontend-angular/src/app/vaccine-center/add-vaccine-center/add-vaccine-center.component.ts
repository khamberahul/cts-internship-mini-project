import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';
import { center } from 'src/center';
import { User } from 'src/user';

@Component({
  selector: 'app-add-vaccine-center',
  templateUrl: './add-vaccine-center.component.html',
  styleUrls: ['./add-vaccine-center.component.css']
})
export class AddVaccineCenterComponent {
  center: center = new center;

  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(employee: center) {
    (employee)
    const center = {
      centerName: employee.centerName,
      centerAddress: employee.centerAddress,
      centerDistrict: employee.centerDistrict,
      centerPinCode: employee.centerPinCode,
      detailsList: [
        {
          vaccineName: employee.vaccineName,
          vaccinePrice: employee.vaccinePrice,
          vaccineQuantity: employee.vaccineQuantity
        }
      ]
    }
    this.adminService.addCenter(center).subscribe((data) => {
      this.router.navigate(["/admin/centers/"])
    },
      (error) => {
        if (error.status == 201) {
          this.router.navigate(["/admin/centers/"])
        } else {
          alert("Data Not Updated")
          console.warn('oops', error.status)
        }

        // alert("Data Not Updated")
        // this.router.navigate(["/error-page"])
      }
    )
  }
}
