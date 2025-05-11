import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { Stock } from '../../models/stock.model';
import { StockService } from '../../services/stock.service';
import { Entrepot } from '../../models/entrepot.model';
import { WarehouseService } from '../../services/warehouse.service';

@Component({
  selector: 'app-stocks',
  templateUrl: './stocks.component.html',
  styleUrls: ['./stocks.component.scss'],
  standalone:false
})
export class StocksComponent implements OnInit {
  stocks: Stock[] = [];
  filteredStocks: Stock[] = [];
  selectedStock: Stock | null = null;
  showDeleteModal = false;
  warehouses: Entrepot[] = [];  // Add this line
  
  searchControl = new FormControl('');
  warehouseFilter = new FormControl('');
  quantityFilter = new FormControl('all');

  constructor(
    private stockService: StockService,
    private warehouseService: WarehouseService  // Add this service
  ) {}

  ngOnInit() {
    this.loadStocks();
    this.loadWarehouses();  // Add this line
    this.setupSearchAndFilters();
  }

  loadWarehouses() {
    this.warehouseService.getAll().subscribe(warehouses => {
      this.warehouses = warehouses;
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
    this.quantityFilter.valueChanges.subscribe(() => this.applyFilters());
  }

  loadStocks() {
    this.stockService.getAll().subscribe(stocks => {
      this.stocks = stocks;
      this.applyFilters();
    });
  }

  applyFilters() {
    let filtered = [...this.stocks];
    const searchTerm = this.searchControl.value?.toLowerCase() || '';
    const warehouseFilter = this.warehouseFilter.value;
    const quantityFilter = this.quantityFilter.value;

    // Apply search
    if (searchTerm) {
      filtered = filtered.filter(stock => 
        stock.produit.nom.toLowerCase().includes(searchTerm) ||
        stock.entrepot.nom.toLowerCase().includes(searchTerm)
      );
    }

    // Apply warehouse filter
    if (warehouseFilter) {
      filtered = filtered.filter(stock => 
        stock.entrepot.nom === warehouseFilter
      );
    }

    // Apply quantity filter
    switch (quantityFilter) {
      case 'low':
        filtered = filtered.filter(stock => 
          stock.quantite <= stock.seuilAlerte
        );
        break;
      case 'normal':
        filtered = filtered.filter(stock => 
          stock.quantite > stock.seuilAlerte
        );
        break;
    }

    this.filteredStocks = filtered;
  }

  confirmDelete(stock: Stock) {
    this.selectedStock = stock;
    this.showDeleteModal = true;
  }

  deleteStock() {
    if (this.selectedStock) {
      this.stockService.delete(this.selectedStock.id).subscribe(() => {
        this.loadStocks();
        this.showDeleteModal = false;
        this.selectedStock = null;
      });
    }
  }

  cancelDelete() {
    this.showDeleteModal = false;
    this.selectedStock = null;
  }
}
