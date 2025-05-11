import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Stock } from '../../../models/stock.model';
import { StockService } from '../../../services/stock.service';
import { ProductService } from '../../../services/product.service';
import { WarehouseService } from '../../../services/warehouse.service';
import { Produit } from '../../../models/produit.model';
import { Entrepot } from '../../../models/entrepot.model';

@Component({
  selector: 'app-stock-form',
  templateUrl: './stock-form.component.html',
  styleUrls: ['./stock-form.component.scss'],
  standalone: false
})
export class StockFormComponent implements OnInit {
  stockForm: FormGroup;
  isEditMode = false;
  stockId: number | null = null;
  products: Produit[] = [];
  warehouses: Entrepot[] = [];

  constructor(
    private fb: FormBuilder,
    private stockService: StockService,
    private productService: ProductService,
    private warehouseService: WarehouseService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.stockForm = this.fb.group({
      produit: ['', Validators.required],
      entrepot: ['', Validators.required],
      quantite: ['', [Validators.required, Validators.min(0)]],
      seuilAlerte: ['', [Validators.required, Validators.min(0)]],
    });
  }

  ngOnInit() {
    this.loadProducts();
    this.loadWarehouses();
    
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.stockId = +id;
      this.loadStock();
    }
  }

  loadProducts() {
    this.productService.getAll().subscribe(products => {
      this.products = products;
    });
  }

  loadWarehouses() {
    this.warehouseService.getAll().subscribe(warehouses => {
      this.warehouses = warehouses;
    });
  }

  loadStock() {
    if (this.stockId) {
      this.stockService.getById(this.stockId).subscribe(stock => {
        this.stockForm.patchValue({
          produit: stock.produit,
          entrepot: stock.entrepot,
          quantite: stock.quantite,
          seuilAlerte: stock.seuilAlerte,
        });
      });
    }
  }

  onSubmit() {
    if (this.stockForm.valid) {
      const stock = this.stockForm.value;
      if (this.isEditMode && this.stockId) {
        this.stockService.update(this.stockId, stock).subscribe(() => {
          this.router.navigate(['/stocks']);
        });
      } else {
        this.stockService.create(stock).subscribe(() => {
          this.router.navigate(['/stocks']);
        });
      }
    }
  }
}