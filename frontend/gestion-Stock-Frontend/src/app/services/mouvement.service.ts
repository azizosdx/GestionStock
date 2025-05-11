import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MouvementStock } from '../models/mouvement-stock.model';


@Injectable({
  providedIn: 'root'
})
export class MouvementService {
    private apiUrl = 'http://localhost:8080/api/mouvement-stock';

  constructor(private http: HttpClient) { }

  getAll(): Observable<MouvementStock[]> {
    return this.http.get<MouvementStock[]>(this.apiUrl);
  }

  getById(id: number): Observable<MouvementStock> {
    return this.http.get<MouvementStock>(`${this.apiUrl}/${id}`);
  }

  create(mouvement: MouvementStock): Observable<MouvementStock> {
    return this.http.post<MouvementStock>(this.apiUrl, mouvement);
  }

  update(id: number, mouvement: MouvementStock): Observable<MouvementStock> {
    return this.http.put<MouvementStock>(`${this.apiUrl}/${id}`, mouvement);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}