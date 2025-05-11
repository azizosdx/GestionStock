package meddeb.aziz.itbs.repository;

import meddeb.aziz.itbs.entity.MouvementStock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MouvementStockRepository extends JpaRepository<MouvementStock, Long> {
    List<MouvementStock> findByProduitId(Long produitId);

    List<MouvementStock> findByEntrepotId(Long entrepotId);

    List<MouvementStock> findByProduitIdAndEntrepotId(Long produitId, Long entrepotId);
}
