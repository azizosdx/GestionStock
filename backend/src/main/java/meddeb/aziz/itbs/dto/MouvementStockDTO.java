package meddeb.aziz.itbs.dto;

import java.sql.Date;

import lombok.Data;
import meddeb.aziz.itbs.entity.TypeMouvementStock;

@Data
public class MouvementStockDTO {
	
    private long id;
    
    private ProduitDTO produit;
    
    private int quantite;
    
    private TypeMouvementStock type; 
    
    private Date date;
    
    private EntrepotDTO entrepot;
}

