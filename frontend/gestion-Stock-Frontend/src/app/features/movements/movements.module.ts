import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MovementsComponent } from './movements.component';
import { MovementFormComponent } from './movement-form/movement-form.component';
import { MovementsRoutingModule } from './movements-routing.module';

@NgModule({
  declarations: [
    MovementsComponent,
    MovementFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MovementsRoutingModule
  ]
})
export class MovementsModule { }
