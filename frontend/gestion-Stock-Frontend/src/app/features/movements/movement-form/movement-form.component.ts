import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MouvementStock } from '../../../models/mouvement-stock.model';
import { ProductService } from '../../../services/product.service';
import { WarehouseService } from '../../../services/warehouse.service';
import { Produit } from '../../../models/produit.model';
import { Entrepot } from '../../../models/entrepot.model';
import { MouvementService } from '../../../services/mouvement.service';

@Component({
  selector: 'app-movement-form',
  templateUrl: './movement-form.component.html',
  styleUrls: ['./movement-form.component.scss'],
  standalone: false
})
export class MovementFormComponent implements OnInit {

  movementForm: FormGroup;
  isEditMode = false;
  movementId: number | null = null;
  products: Produit[] = [];
  warehouses: Entrepot[] = [];

  constructor(
    private fb: FormBuilder,
    private movementService: MouvementService,
    private productService: ProductService,
    private warehouseService: WarehouseService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.movementForm = this.fb.group({
      produit: ['', Validators.required],
      entrepot: ['', Validators.required],
      quantite: ['', [Validators.required, Validators.min(1)]],
      type: ['entrée', Validators.required],
      
    });
  }

  ngOnInit() {
    // Load data first
    Promise.all([
      this.loadProducts(),
      this.loadWarehouses()
    ]).then(() => {
      // After data is loaded, check for edit mode
      const id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.isEditMode = true;
        this.movementId = +id;
        this.loadMovement();
      }
    });
  }

  loadProducts() {
    return new Promise<void>((resolve) => {
      this.productService.getAll().subscribe(products => {
        this.products = products;
        console.log('Products loaded:', this.products);
        resolve();
      });
    });
  }

  loadWarehouses() {
    return new Promise<void>((resolve) => {
      this.warehouseService.getAll().subscribe(warehouses => {
        this.warehouses = warehouses;
        console.log('Warehouses loaded:', this.warehouses);
        resolve();
      });
    });
  }

  loadMovement() {
    if (this.movementId) {
      this.movementService.getById(this.movementId).subscribe(movement => {
        this.movementForm.patchValue({
          produit: movement.produit,
          entrepot: movement.entrepot,
          quantite: movement.quantite,
          type: movement.type,
          date: movement.date
        });
      });
    }
  }

  onSubmit() {
    if (this.movementForm.valid) {
      const movement: MouvementStock = {
        ...this.movementForm.value,
        id: this.movementId || 0
      };
      
      if (this.isEditMode && this.movementId) {
        this.movementService.update(this.movementId, movement).subscribe(() => {
          this.router.navigate(['/movements']);
        });
      } else {
        this.movementService.create(movement).subscribe(() => {
          this.router.navigate(['/movements']);
        });
      }
    }
  }
}