import { Component } from "@angular/core";
import { formatDate } from '@angular/common'
import { ToastrService } from "ngx-toastr";

import { AdminService } from "../service/admin.service";
import { AuthService } from "../service/auth.service";


@Component({
    selector: 'update-vaccine-status',
    templateUrl: './update-vaccine-status.component.html',
    styleUrls: ['./update-vaccine-status.component.css']
})
export class UpdateVaccineStatusComponent {
    // TODO: create a User interface to store users. "Any" type is just for testing sake.
    users: any = [];
    tableSearch: string = '';
    clickedUser: any;
    formatDate = formatDate;

    constructor(private adminService: AdminService, private authService: AuthService, private toast: ToastrService) { }

    ngOnInit(): void {
        this.authService.checkState()

        this.populateTable();
    }

    // TODO
    doseComplete(userId: string, fullName: string): void {
        const _token: string = localStorage.getItem('cvs_token') ?? "";

        this.adminService.changeVaccinationStatus(userId, _token, 'dose1').subscribe((data: any) => {
            this.populateTable();
            this.toast.success("", `${fullName} marked as vaccinated 👍`)
        }, (error: any) => {
            this.toast.error("", `There was an error while marking ${fullName} vaccinated. 👎`)
        })



    }

    populateTable() {
        const _token = localStorage.getItem('cvs_token');

        this.adminService.getUsers(_token).subscribe((data) => {
            this.users = data;

            this.users = this.users.filter((user: any) => user?.vaccinationDate !== null && user?.userStatus.toLowerCase() === 'accept')
        })
    }

    setClickedUser(id: number) {
        this.clickedUser = this.users.filter((user: any) => user?.userId === id)[0];
    }

}