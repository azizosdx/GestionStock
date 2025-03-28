package meddeb.aziz.itbs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "produit_id")
	    @JsonBackReference(value = "produit")
	    @JsonIgnore
	    private Produit produit;
	    
	    @ManyToOne
	    @JoinColumn(name = "entrepot_id")
	    @JsonBackReference(value = "entrepot")
	    @JsonIgnore
	    private Entrepot entrepot;
	    
	    private int quantite;
	    
	    private int seuilAlerte;
	
}
