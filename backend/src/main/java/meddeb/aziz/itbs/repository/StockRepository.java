package meddeb.aziz.itbs.repository;


import meddeb.aziz.itbs.entity.Entrepot;
import meddeb.aziz.itbs.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import meddeb.aziz.itbs.entity.Stock;


public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByProduitAndEntrepot(Produit produit, Entrepot entrepot);
}
