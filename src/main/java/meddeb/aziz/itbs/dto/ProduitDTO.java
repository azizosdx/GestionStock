package meddeb.aziz.itbs.dto;

import lombok.Data;

@Data
public class ProduitDTO {
    private Long id;
    
    private String nom;
    
    private int quantite;
    
    private String categorie;
}
