import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder} from "@angular/forms";
import {AccountsService} from "../services/accounts.service";
import {catchError, Observable, throwError} from "rxjs";
import {AccountDetails} from "../model/account.model";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
    accountFormGroup! : FormGroup;
    currentPage : number = 0;
    pageSize : number = 5;
    accountObservable! : Observable<AccountDetails>
    operationFormGroup! : FormGroup;
    errorMessage! : string;

  constructor(private fb: FormBuilder, private accountService : AccountsService) { }

  ngOnInit(): void {
    this.accountFormGroup = this.fb.group({
      accountId : this.fb.control('')
    });
    this.operationFormGroup = this.fb.group({
      operationType : this.fb.control(null),
      amount : this.fb.control(0),
      description : this.fb.control(null),
      recipientAccount : this.fb.control(null)

    })
  }

  handleSearchAccounts() {
    let accountId : string = this.accountFormGroup.value.accountId;
    this.accountObservable = this.accountService.getAccount(accountId, this.currentPage, this.pageSize).pipe(
      catchError(err=>{
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  gotoPage(page : number) {
    this.currentPage = page;
    this.handleSearchAccounts()
  }

  handleAccountOperations() {
    let accountId : string = this.accountFormGroup.value.accountId;
    let operationType : string = this.operationFormGroup.value.operationType;
    let amount : number = this.operationFormGroup.value.amount;
    let description : string = this.operationFormGroup.value.description;
    let recipientId : string = this.operationFormGroup.value.recipientAccount;
    if(operationType=='DEBIT'){
      this.accountService.debit(accountId, amount, description).subscribe({
        next : (data)=>{
          alert("Success debit");
          this.handleSearchAccounts();
        },
        error : (err => {
          console.log(err);
        })
      });
    }
    else if(operationType=='CREDIT'){
      this.accountService.credit(accountId, amount, description).subscribe({
        next : (data)=>{
          alert("Success credit");
          this.handleSearchAccounts();
        },
        error : (err => {
          console.log(err);
        })
      });
    }
    else if(operationType == 'TRANSFER'){
      this.accountService.transfer(accountId, recipientId, amount, description).subscribe({
        next : (data)=>{
          alert("Success transfer");
          this.handleSearchAccounts();
        },
        error : (err => {
          console.log(err);
        })
      });
    }
    this.operationFormGroup.reset();
  }
}
