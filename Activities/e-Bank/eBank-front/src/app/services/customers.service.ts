import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../models/customer.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CustomersService {
  constructor(private http: HttpClient) { }

  public getCustomers() :Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.backendHost+"/api/v1/customers");
  }

  public getCustomer(id: number) :Observable<Customer>{
    return this.http.get<Customer>(environment.backendHost+"/api/v1/customers/"+id);
  }

  public searchCustomers(keyword : string):Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.backendHost+"/api/v1/customers/search?keyword="+keyword);
  }

  public saveCustomer(customer : Customer):Observable<Customer>{
    return this.http.post<Customer>(environment.backendHost+"/api/v1/customers", customer);
  }

  public deleteCustomer(id: number){
    return this.http.delete(environment.backendHost+"/api/v1/customers/"+id);
  }

  updateCustomer(id:number , name: string, email: string){
    const data = {name: name, email: email};
    let queryParams = new HttpParams();
    queryParams = queryParams.append("name",name);
    queryParams = queryParams.append("email",email);
    return this.http.put(environment.backendHost+"/api/v1/customers/"+id,{}, {params: queryParams});
  }
}
