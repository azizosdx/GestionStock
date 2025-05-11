import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MouvementStock } from '../../models/mouvement-stock.model';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { MouvementService } from '../../services/mouvement.service';
import { ProductService } from '../../services/product.service';
import { WarehouseService } from '../../services/warehouse.service';
import { Produit } from '../../models/produit.model';
import { Entrepot } from '../../models/entrepot.model';

@Component({
  selector: 'app-movements',
  templateUrl: './movements.component.html',
  styleUrls: ['./movements.component.scss'],
  standalone: false
})
export class MovementsComponent implements OnInit {
  movements: MouvementStock[] = [];
  filteredMovements: MouvementStock[] = [];
  showEntryForm = false;
  showExitForm = false;
  showDeleteModal = false;
  selectedMovement: MouvementStock | null = null;
  warehouses: Entrepot[] = [];
  products: Produit[] = [];
  movementForm: FormGroup;
  searchControl = new FormControl('');
  warehouseFilter = new FormControl();
  typeFilter = new FormControl('');

  constructor(
    private fb: FormBuilder,
    private movementService: MouvementService,
    private productService: ProductService,
    private warehouseService: WarehouseService
  ) {
    this.movementForm = this.fb.group({
      produit: ['', Validators.required],
      entrepot: ['', Validators.required],
      quantite: ['', [Validators.required, Validators.min(1)]],
      type: ['', Validators.required],
      
    });
  }

  ngOnInit() {
    this.loadProducts();
    this.loadWarehouses();
    this.setupSearchAndFilters();
    this.loadMovements();
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

  loadMovements() {
    this.movementService.getAll().subscribe(movements => {
      this.movements = movements;
      this.applyFilters();
    });
  }

  setupSearchAndFilters() {
    this.searchControl.valueChanges
      .pipe(
        debounceTime(300),
        distinctUntilChanged()
      )
      .subscribe(() => this.applyFilters());

    this.warehouseFilter.valueChanges.subscribe(() => this.applyFilters());
    this.typeFilter.valueChanges.subscribe(() => this.applyFilters());
  }

  applyFilters() {
    let filtered = [...this.movements];
    const searchTerm = this.searchControl.value?.toLowerCase() || '';
    const warehouseFilter = this.warehouseFilter.value;
    const typeFilter = this.typeFilter.value;

    if (searchTerm) {
      filtered = filtered.filter(movement => 
        movement.produit.nom.toLowerCase().includes(searchTerm) ||
        movement.entrepot.nom.toLowerCase().includes(searchTerm)
      );
    }

    if (warehouseFilter) {
      filtered = filtered.filter(movement => movement.entrepot.id === warehouseFilter.id);
    }

    if (typeFilter) {
      filtered = filtered.filter(movement => movement.type === typeFilter);
    }

    this.filteredMovements = filtered;
  }

  openEntryForm() {
    this.showEntryForm = true;
    this.movementForm.patchValue({ type: 'ENTREE' });
  }

  openExitForm() {
    this.showExitForm = true;
    this.movementForm.patchValue({ type: 'SORTIE' });
  }

  isEditMode = false;
  editingMovement: MouvementStock | null = null;

  editMovement(movement: MouvementStock) {
    this.isEditMode = true;
    this.editingMovement = movement;
    this.movementForm.patchValue({
      produit: movement.produit,
      entrepot: movement.entrepot,
      quantite: movement.quantite,
      type: movement.type,
      date: movement.date
    });
    this.showEntryForm = true;
  }

  onSubmit() {
    if (this.movementForm.valid) {
      const formValue = this.movementForm.value;
      const movement: MouvementStock = {
        ...formValue,
        id: this.isEditMode ? this.editingMovement!.id : 0
      };

      if (this.isEditMode) {
        this.movementService.update(movement.id, movement).subscribe({
          next: () => {
            this.loadMovements();
            this.closeForm();
          },
          error: (error) => {
            console.error('Error updating movement:', error);
          }
        });
      } else {
        this.movementService.create(movement).subscribe({
          next: () => {
            this.loadMovements();
            this.closeForm();
          },
          error: (error) => {
            console.error('Error creating movement:', error);
          }
        });
      }
    }
  }

  closeForm() {
    this.showEntryForm = false;
    this.showExitForm = false;
    this.isEditMode = false;
    this.editingMovement = null;
    this.movementForm.reset();
  }

  confirmDelete(movement: MouvementStock) {
    this.selectedMovement = movement;
    this.showDeleteModal = true;
  }

  deleteMovement() {
    if (this.selectedMovement) {
      this.movementService.delete(this.selectedMovement.id).subscribe({
        next: () => {
          this.movements = this.movements.filter(m => m.id !== this.selectedMovement?.id);
          this.applyFilters();
          this.showDeleteModal = false;
          this.selectedMovement = null;
        },
        error: (error) => {
          console.error('Error deleting movement:', error);
          // You might want to show an error message to the user here
        }
      });
    }
  }

  cancelDelete() {
    this.showDeleteModal = false;
    this.selectedMovement = null;
  }
}
