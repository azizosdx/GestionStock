package meddeb.aziz.itbs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import meddeb.aziz.itbs.entity.Stock;


public interface StockRepository extends JpaRepository<Stock, Long> {

}
