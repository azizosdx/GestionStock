import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { WarehouseService } from '../../services/warehouse.service';
import { StockService } from '../../services/stock.service';
import { MouvementService } from '../../services/mouvement.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  standalone: false
})
export class DashboardComponent implements OnInit {
  totalProducts = 0;
  totalWarehouses = 0;
  lowStockAlerts = 0;
  recentMovements = 0;
  stockDistribution: any[] = [];
  recentMovementsList: any[] = [];

  constructor(
    private productService: ProductService,
    private warehouseService: WarehouseService,
    private stockService: StockService,
    private movementService: MouvementService
  ) {}

  ngOnInit() {
    this.loadDashboardData();
  }

  loadDashboardData() {
    forkJoin({
      products: this.productService.getAll(),
      warehouses: this.warehouseService.getAll(),
      stocks: this.stockService.getAll(),
      movements: this.movementService.getAll()
    }).subscribe({
      next: (data) => {
        this.totalProducts = data.products.length;
        this.totalWarehouses = data.warehouses.length;
        
        // Calculate low stock alerts
        this.lowStockAlerts = data.stocks.filter(stock => 
          stock.quantite <= stock.seuilAlerte
        ).length;
        
        // Get recent movements (last 30 days)
        const thirtyDaysAgo = new Date();
        thirtyDaysAgo.setDate(thirtyDaysAgo.getDate() - 30);
        
        this.recentMovementsList = data.movements
          .filter(movement => new Date(movement.date) >= thirtyDaysAgo)
          .sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime())
          .slice(0, 4); // Get only the 5 most recent movements

        this.recentMovements = this.recentMovementsList.length;

        // Calculate stock distribution by warehouse
        this.calculateStockDistribution(data.stocks);
      },
      error: (error) => {
        console.error('Error loading dashboard data:', error);
      }
    });
  }

  private calculateStockDistribution(stocks: any[]) {
    // Group stocks by warehouse
    const distribution = new Map<string, number>();
    
    stocks.forEach(stock => {
      const warehouseName = stock.entrepot.nom;
      const currentQuantity = distribution.get(warehouseName) || 0;
      distribution.set(warehouseName, currentQuantity + stock.quantite);
    });

    // Convert to array format for display
    this.stockDistribution = Array.from(distribution).map(([warehouse, quantity]) => ({
      warehouse,
      quantity
    }));
  }

  getMaxQuantity(): number {
    if (!this.stockDistribution || this.stockDistribution.length === 0) {
      return 0;
    }
    return Math.max(...this.stockDistribution.map(item => item.quantity));
  }
}
