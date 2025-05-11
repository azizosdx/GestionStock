import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Produit } from '../../../models/produit.model';
import { ProductService } from '../../../services/product.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss'],
  standalone:false
})
export class ProductFormComponent implements OnInit {
  productForm: FormGroup;
  isEditMode : boolean | undefined;
  productId: any ;

  constructor(
    private fb: FormBuilder,
    @Inject(ProductService) private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.productForm = this.fb.group({
      nom: ['', Validators.required],
      categorie: ['', Validators.required],
      prix: ['', [Validators.required, Validators.min(0)]],
      fournisseur: ['', Validators.required],
      seuilMin: ['', [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (params['id'] !== undefined && params['id'] !== null) {
        this.isEditMode = true;
        this.productId = +params['id'];
        this.loadProduct();
      } else {
        this.isEditMode = false;
      }
    });
  }
  

  loadProduct() {
    this.productService.getById(this.productId).subscribe((product: Produit) => {
      this.productForm.patchValue(product);
    });
  }

  onSubmit() {
    if (this.productForm.valid) {
      const product = this.productForm.value;
      if (this.isEditMode) {
        this.productService.update(this.productId, product).subscribe(() => {
          this.router.navigate(['/products']);
        });
      } else {
        this.productService.create(product).subscribe(() => {
          this.router.navigate(['/products']);
        });
      }
    }
  }
}