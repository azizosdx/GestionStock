import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovementsComponent } from './movements.component';
import { MovementFormComponent } from './movement-form/movement-form.component';

const routes: Routes = [
  { path: '', component: MovementsComponent },
  { path: 'add', component: MovementFormComponent },
  { path: 'edit/:id', component: MovementFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MovementsRoutingModule { }
