import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WarehousesComponent } from './warehouses.component';
import { WarehouseFormComponent } from './warehouse-form/warehouse-form.component';

const routes: Routes = [
  { path: '', component: WarehousesComponent },
  { path: 'add', component: WarehouseFormComponent },
  { path: 'edit/:id', component: WarehouseFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WarehousesRoutingModule { }
