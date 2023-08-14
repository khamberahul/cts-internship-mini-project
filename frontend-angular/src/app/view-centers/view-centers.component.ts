import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { vaccine } from 'src/vaccine';
import { AdminService } from '../service/admin.service';
import { AuthService } from '../service/auth.service';

import { isEqual } from 'lodash-es'

const { log } = console;

@Component({
  selector: 'app-view-centers',
  templateUrl: './view-centers.component.html',
  styleUrls: ['./view-centers.component.css']
})
export class ViewCentersComponent {
  centers: any = []
  tableSearch: string = '';

  clickedDetailsList: any = []
  clickedCenter: any = {};
  vaccine: any = {}
  editCenterName: any;

  constructor(private adminService: AdminService, private authService: AuthService, private route: Router, private toast: ToastrService) { }

  ngOnInit(): void {
    this.authService.checkState()

    this.populateTable();
  }
  addCenter() {
    this.route.navigate(["/admin/addcenter"]);
  }

  onEditCenter(centerId: string): void {
    let _center: any = this.centers.filter((c: any) => c.centerId === centerId)[0];

    this.adminService.editCenter(centerId, _center)
      .subscribe((data) => {
        this.populateTable();
        this.toast.success('Details saved!')
      }, (error) => {
        this.toast.error('There was a problem with saving details.')
      })


  }

  addVaccine(centerId: string) {
    let _center: any = this.centers.filter((c: any) => c.centerId === centerId)[0];

    // Validation
    if (this.vaccine?.vaccineName === undefined) {
      this.toast.warning('', 'Please mention Vaccine Name.', {
        positionClass: 'toast-bottom-right'
      })
      return;
    } else if (this.vaccine?.vaccinePrice === undefined) {
      this.toast.warning('', 'Please mention Vaccine Price.')
      return;
    } else if (this.vaccine?.vaccineQuantity === undefined) {
      this.toast.warning('', 'Please mention Vaccine Quantity.')
      return;
    }


    _center.detailsList = null;
    _center.detailsList = [{
      vaccineName: this.vaccine.vaccineName,
      vaccinePrice: this.vaccine.vaccinePrice,
      vaccineQuantity: this.vaccine.vaccineQuantity
    }];


    this.adminService.editCenter(centerId, _center)
      .subscribe((data: any) => {
        this.populateTable();
        this.toast.success('', `Details added.`)
        this.vaccine = {};

      }, (error) => {
        console.warn(error)
        this.toast.warning('', `There was an error while adding vaccines.`,)

      })
  }

  onDelete(centerId: number): void {
    this.adminService.deleteCenter(centerId).subscribe((data: any) => {
      this.populateTable();
    })
  }

  populateTable() {
    this.adminService.getCenters().subscribe((data: any) => {
      this.centers = data;
    })
  }

  setClickedCenter(id: number) {
    this.clickedCenter = this.centers.filter((center: any) => center?.centerId === id)[0];

    this.editCenterName = this.clickedCenter?.centerName;

    this.clickedDetailsList = [...this.clickedCenter?.detailsList];
  }


  onDeleteInventory(centerId: string, detailId: string) {
    const _center: any = this.clickedCenter;

    const _detailsList: any = _center.detailsList;

    _center.detailsList = null;
    _center.detailsList = _detailsList.filter((d: any) => d?.vaccineId !== detailId);


    this.adminService.deleteVaccineDetail(detailId)
      .subscribe((data: any) => {
        this.populateTable();
        this.toast.success('', `Vaccine was removed.`)

      }, (error) => {
        console.warn(error)
        this.toast.warning('', `There was an error while removing vaccines.`,)

      })
  }
}
