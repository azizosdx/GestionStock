package meddeb.aziz.itbs.dto;

import lombok.Data;

@Data
public class StockDTO {

    private Long id;

    private ProduitDTO produit;

    private int quantite;

    private EntrepotDTO entrepot;
}
