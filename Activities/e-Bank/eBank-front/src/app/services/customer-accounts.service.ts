import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../models/customer.model";
import {environment} from "../../environments/environment";
import {AccountDetails} from "../models/account.model";

@Injectable({
  providedIn: 'root'
})
export class CustomerAccountsService {

  constructor(private http: HttpClient) { }

  public getCustomerAccounts(customerId: number): Observable<Array<AccountDetails>> {
    return this.http.get<Array<AccountDetails>>(environment.backendHost + "/api/v1/customers/"+customerId+"/accounts");
  }

}
