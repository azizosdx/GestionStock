package meddeb.aziz.itbs.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import meddeb.aziz.itbs.dto.EntrepotDTO;
import meddeb.aziz.itbs.dto.ProduitDTO;
import meddeb.aziz.itbs.entity.Entrepot;
import meddeb.aziz.itbs.entity.Produit;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    Produit produitDTOToEntity (ProduitDTO produitDTO);
	
    ProduitDTO entityToProduitDTO (Produit produit);
	
    List<ProduitDTO> entityToProduitDTO(List<Produit> produits);
	
}
