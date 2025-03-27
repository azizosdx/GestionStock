package meddeb.aziz.itbs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
    @Column(unique = true, nullable = false)
	private String nom ; 
	
	@Column
	private String categorie;
	
	@Column
	private double prix;
	
	@Column
	private String fournissuer;
	
	@Column
	private int seuilMin;
	
}
