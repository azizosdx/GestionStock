import { Component, OnInit } from '@angular/core';
import { Entrepot } from '../../models/entrepot.model';
import { WarehouseService } from '../../services/warehouse.service';

@Component({
  selector: 'app-warehouses',
  templateUrl: './warehouses.component.html',
  styleUrls: ['./warehouses.component.scss'],
  standalone:false
})
export class WarehousesComponent implements OnInit {
  warehouses: Entrepot[] = [];
  selectedWarehouse: Entrepot | null = null;
  showDeleteModal = false;

  constructor(private warehouseService: WarehouseService) {}

  ngOnInit() {
    this.loadWarehouses();
  }

  loadWarehouses() {
    this.warehouseService.getAll().subscribe(warehouses => {
      this.warehouses = warehouses;
    });
  }

  confirmDelete(warehouse: Entrepot) {
    this.selectedWarehouse = warehouse;
    this.showDeleteModal = true;
  }

  deleteWarehouse() {
    if (this.selectedWarehouse) {
      this.warehouseService.delete(this.selectedWarehouse.id).subscribe(() => {
        this.loadWarehouses();
        this.showDeleteModal = false;
        this.selectedWarehouse = null;
      });
    }
  }

  cancelDelete() {
    this.showDeleteModal = false;
    this.selectedWarehouse = null;
  }
}
