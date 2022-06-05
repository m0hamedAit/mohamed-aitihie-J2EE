import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AccountDetails} from "../models/account.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {

  constructor(private http : HttpClient) { }

  public getAccount(accountId : string, page : number, size : number):Observable<AccountDetails>{
    return this.http.get<AccountDetails>(environment.backendHost+"/api/v1/accounts/"+accountId+"/pageOperations?page="+page+"&size="+size);
  }

  public debit(accountId : string, amount : number, description:string){
    let data = {accountId,  amount, description};
    return this.http.post(environment.backendHost+"/api/v1/accounts/debit", data);
  }

  public credit(accountId : string, amount : number, description:string){
    let data = {accountId,  amount, description};
    return this.http.post(environment.backendHost+"/api/v1/accounts/credit", data);
  }

  public transfer(senderId : string, recipientId : string, amount : number, description:string){
    let data = {senderId, recipientId, amount, description};
    return this.http.post(environment.backendHost+"/api/v1/accounts/transfer", data);
  }
}
