import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StocksRoutingModule } from './stocks-routing.module';
import { StocksComponent } from './stocks.component';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { StockService } from '../../services/stock.service';
import { StockFormComponent } from './stock-form/stock-form.component';


@NgModule({
  declarations: [StocksComponent,StockFormComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    StocksRoutingModule
  ],
  providers: [StockService]
})
export class StocksModule { }
