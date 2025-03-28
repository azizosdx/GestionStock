package meddeb.aziz.itbs.repository;

import meddeb.aziz.itbs.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    public Produit findByNom(String nom);
}
