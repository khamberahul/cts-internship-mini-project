import { Component } from "@angular/core";
import { AdminService } from "../service/admin.service";
import { AuthService } from "../service/auth.service";
import { environment } from "src/environments/environment";
import { ToastrService } from "ngx-toastr";

const { usersBaseUrl } = environment;
@Component({
    selector: 'view-user',
    templateUrl: './view-users.component.html',
    styleUrls: ['./view-users.component.css']
})
export class ViewUserComponent {
    users: any = [];
    tableSearch: string = '';

    total: number = 10
    approved: number = 0
    rejected: number = 0
    pending: number = 0

    clickedUser: any;

    constructor(private adminService: AdminService, private authService: AuthService, private toastr: ToastrService) { }

    ngOnInit(): void {
        this.authService.checkState()

        this.populateTable();


    }

    // TODO
    onAccept(userId: string, fullName: any): void {
        const _token: string = localStorage.getItem('cvs_token') ?? "";

        this.adminService.changeUserStatus(userId, _token, 'accept').subscribe((data) => {
            this.populateTable();
            this.toastr.success('', `Approved ${fullName}`)

        }, (error: any) => {
            console.warn("Accepting user error")
        })

    }

    onReject(userId: string, fullName: any): void {
        const _token: string = localStorage.getItem('cvs_token') ?? "";
        this.adminService.changeUserStatus(userId, _token, 'reject').subscribe((data) => {
            this.populateTable();
            this.toastr.error('', `Rejected user: ${fullName}`);

        }, (error: any) => {
            console.warn("Rejecting user error")
        })


    }

    populateTable() {
        const _token = localStorage.getItem('cvs_token');



        this.adminService.getUsers(_token).subscribe((data) => {
            this.users = data;
            this.approved = this.users.filter((u: any) => u.userStatus.toLowerCase() === 'accept').length;
            this.rejected = this.users.filter((u: any) => u.userStatus.toLowerCase() === 'reject').length;
            this.pending = this.users.filter((u: any) => u.userStatus.toLowerCase() === 'pending').length;
        })



    }

    setClickedUser(id: number) {
        this.clickedUser = this.users.filter((user: any) => user?.userId === id)[0];
    }
}