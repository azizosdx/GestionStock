import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { WarehousesComponent } from './warehouses.component';
import { ReactiveFormsModule } from '@angular/forms';
import { WarehouseFormComponent } from './warehouse-form/warehouse-form.component';
import { WarehousesRoutingModule } from './warehouses-routing.module';


@NgModule({
  declarations: [WarehousesComponent,
    WarehouseFormComponent
  ],
  imports: [
    CommonModule,
    WarehousesRoutingModule,
    ReactiveFormsModule,
    RouterModule
  ]
})
export class WarehousesModule { }
