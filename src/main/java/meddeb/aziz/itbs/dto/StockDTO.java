package meddeb.aziz.itbs.dto;

import lombok.Data;

@Data
public class StockDTO {
    private Long id;
    private String produitNom;
    private int quantite;
    private String entrepotNom;
}
