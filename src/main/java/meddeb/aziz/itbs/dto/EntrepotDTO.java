package meddeb.aziz.itbs.dto;

import java.util.List;

import lombok.Data;

@Data
public class EntrepotDTO {
    private Long id;
    
    private String nom;
    
    private String adresse;
    
    private List<StockDTO> stocks;
}