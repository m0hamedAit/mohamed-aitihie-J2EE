import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {CustomersComponent} from "./customers/customers.component";
import {AccountsComponent} from "./accounts/accounts.component";
import {Page404Component} from "./page404/page404.component";
import {CustomerAccountsComponent} from "./customer-accounts/customer-accounts.component";
import {NewCustomerComponent} from "./new-customer/new-customer.component";

const routes: Routes = [
  {"path": "", "redirectTo": "home", "pathMatch": "full"},
  {"path": "home", component: HomeComponent},
  {"path": "customers", component: CustomersComponent},
  {"path": "accounts", component: AccountsComponent},
  {"path": "customer_accounts", component: CustomerAccountsComponent},
  {"path": "new-customer", component: NewCustomerComponent},
  {"path": "**", component: Page404Component}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
