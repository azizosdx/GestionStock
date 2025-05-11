import { Component, Inject, OnInit } from '@angular/core';

import { Produit } from '../../models/produit.model';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
  standalone:false
})
export class ProductsComponent implements OnInit {
  products: Produit[] = [];
  selectedProduct: Produit | null = null;
  showDeleteModal = false;

  constructor(@Inject(ProductService) private productService: ProductService) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getAll().subscribe((products: Produit[]) => {
      this.products = products;
    });
  }

  confirmDelete(product: Produit) {
    this.selectedProduct = product;
    this.showDeleteModal = true;
  }

  deleteProduct() {
    if (this.selectedProduct) {
      this.productService.delete(this.selectedProduct.id).subscribe(() => {
        this.loadProducts();
        this.showDeleteModal = false;
        this.selectedProduct = null;
      });
    }
  }

  cancelDelete() {
    this.showDeleteModal = false;
    this.selectedProduct = null;
  }
}
