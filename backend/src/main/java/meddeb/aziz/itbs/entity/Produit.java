package meddeb.aziz.itbs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
    @Column(nullable = false, unique = true)
	private String nom ; 

	private String categorie;

	private double prix;

	private String fournisseur;

	private int seuilMin;

	@OneToMany(mappedBy="produit")
	private List<Stock> Stocks = new ArrayList<Stock>();


}
