import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CustomersService} from "../services/customers.service";
import {catchError, Observable, throwError} from "rxjs";
import {Customer} from "../models/customer.model";
import {AccountDetails} from "../models/account.model";
import {CustomerAccountsService} from "../services/customer-accounts.service";

@Component({
  selector: 'app-customer-accounts',
  templateUrl: './customer-accounts.component.html',
  styleUrls: ['./customer-accounts.component.css']
})
export class CustomerAccountsComponent implements OnInit {
  customerFormGroup! : FormGroup;
  errorMessageCustomer! : string;
  errorMessageAccounts! : string;
  customerObservable! : Observable<Customer>;
  customerAccounts! : Observable<Array<AccountDetails>>;
  currentPage : number = 0;
  pageSize : number = 5;

  constructor(private fb: FormBuilder, private customerService : CustomersService, private customerAccountsService: CustomerAccountsService) { }

  ngOnInit(): void {
    this.customerFormGroup = this.fb.group({
      customerId : this.fb.control('')
    });
  }

  public handleSearchCustomer() {
    let customerId = this.customerFormGroup.value.customerId;
    this.customerObservable = this.customerService.getCustomer(customerId).pipe(
      catchError(err=>{
        this.errorMessageCustomer = err.message;
        return throwError(err);
      })
    );
    this.customerAccounts = this.customerAccountsService.getCustomerAccounts(customerId).pipe(
      catchError(err=>{
        this.errorMessageAccounts = err.message;
        return throwError(err);
      })
    );
  }
}
