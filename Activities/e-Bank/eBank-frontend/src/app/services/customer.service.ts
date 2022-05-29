import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'  // disponible dans root, donc pas besoin de le déclarer dans "providers" dans "app.module.ts"
})
export class CustomerService {

  constructor(private http:HttpClient) {}

  public getCustomers():Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.backendHost+"/customers")
  }

  public searchCustomers(keyword : string):Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(environment.backendHost+"/customers/search?keyword="+keyword);
  }

  public saveCustomer(customer : Customer):Observable<Customer>{
    return this.http.post<Customer>(environment.backendHost+"/customers", customer);
  }

  public deleteCustomer(id: number){
    return this.http.delete(environment.backendHost+"/customers/"+id);
  }

  updateCustomer(id:number , name: string, email: string){
    const data = {name: name, email: email};
    let queryParams = new HttpParams();
    queryParams = queryParams.append("name",name);
    queryParams = queryParams.append("email",email);
    return this.http.put(environment.backendHost+"/customers/"+id,{}, {params: queryParams});
  }
}
