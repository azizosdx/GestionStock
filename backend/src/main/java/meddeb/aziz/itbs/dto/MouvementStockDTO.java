package meddeb.aziz.itbs.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;
import meddeb.aziz.itbs.entity.TypeMouvementStock;

@Data
public class MouvementStockDTO {
	
    private Long id;
    
    private String produitNom;
    
    private int quantite;
    
    private TypeMouvementStock type; 
    
    private Date date;
    
    private String entrepotNom;
}

