import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produit } from '../models/produit.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:8080/api/produit'; // Adjust this URL to match your backend API

  constructor(private http: HttpClient) { }

  getAll(): Observable<Produit[]> {
    return this.http.get<Produit[]>(this.apiUrl);
  }

  getById(id: number): Observable<Produit> {
    return this.http.get<Produit>(`${this.apiUrl}/${id}`);
  }

  create(produit: Produit): Observable<Produit> {
    return this.http.post<Produit>(this.apiUrl, produit);
  }

  update(id: number, produit: Produit): Observable<Produit> {
    return this.http.put<Produit>(`${this.apiUrl}/${id}`, produit);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // Additional method to get products below threshold
  getLowStockProducts(): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/low-stock`);
  }

  // Method to search products by category
  getByCategory(category: string): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/category/${category}`);
  }

  // Method to search products by name
  searchByName(name: string): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/search?name=${name}`);
  }
}