import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../model/customer.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../services/accounts.service";
import {CustomerService} from "../services/customer.service";

@Component({
  selector: 'app-customer-accounts',
  templateUrl: './customer-accounts.component.html',
  styleUrls: ['./customer-accounts.component.css']
})
export class CustomerAccountsComponent implements OnInit {
  customerFormGroup! : FormGroup;
  customerId! : String;
  customer! : Customer;

  constructor(private route: ActivatedRoute, private router : Router, private fb: FormBuilder, private customerService : CustomerService) {
    this.customer = this.router.getCurrentNavigation()?.extras.state as Customer;
  }

  ngOnInit(): void {
    this.customerId = this.route.snapshot.params["id"];
    this.customerFormGroup = this.fb.group({
      customerId: this.fb.control(this.customerId),
      customerName: this.fb.control(this.customer.name),
      customerEmail: this.fb.control(this.customer.email),
    });
  }

  handleUpdateCustomer() {
    let customerInfo = this.customerFormGroup.value;
    this.customerService.updateCustomer(customerInfo.customerId,
      customerInfo.customerName, customerInfo.customerEmail).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['/customers']);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
