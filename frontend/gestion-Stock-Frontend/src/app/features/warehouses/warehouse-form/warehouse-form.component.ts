import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Entrepot } from '../../../models/entrepot.model';
import { WarehouseService } from '../../../services/warehouse.service';

@Component({
  selector: 'app-warehouse-form',
  templateUrl: './warehouse-form.component.html',
  styleUrls: ['./warehouse-form.component.scss'],
  standalone:false
})
export class WarehouseFormComponent implements OnInit {
  warehouseForm: FormGroup;
  isEditMode = false;
  warehouseId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private warehouseService: WarehouseService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.warehouseForm = this.fb.group({
      nom: ['', Validators.required],
      adresse: ['', Validators.required],
      capacite: ['', [Validators.required, Validators.min(0)]],
    });
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.warehouseId = +id;
      this.loadWarehouse();
    }
  }

  loadWarehouse() {
    if (this.warehouseId) {
      this.warehouseService.getById(this.warehouseId).subscribe(warehouse => {
        this.warehouseForm.patchValue(warehouse);
      });
    }
  }

  onSubmit() {
    if (this.warehouseForm.valid) {
      const warehouse = this.warehouseForm.value;
      if (this.isEditMode && this.warehouseId) {
        this.warehouseService.update(this.warehouseId, warehouse).subscribe(() => {
          this.router.navigate(['/warehouses']);
        });
      } else {
        this.warehouseService.create(warehouse).subscribe(() => {
          this.router.navigate(['/warehouses']);
        });
      }
    }
  }
}