import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StocksComponent } from './stocks.component';
import { StockFormComponent } from './stock-form/stock-form.component';

const routes: Routes = [
  { path: '', component: StocksComponent },
  { path: 'add', component: StockFormComponent },
  { path: 'edit/:id', component: StockFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StocksRoutingModule { }
