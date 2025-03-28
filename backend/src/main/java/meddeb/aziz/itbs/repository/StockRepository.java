package meddeb.aziz.itbs.repository;

import meddeb.aziz.itbs.entity.Entrepot;
import meddeb.aziz.itbs.entity.Produit;
import meddeb.aziz.itbs.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
