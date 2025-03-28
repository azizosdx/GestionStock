package meddeb.aziz.itbs.mapper;

import meddeb.aziz.itbs.dto.ProduitDTO;
import meddeb.aziz.itbs.entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,componentModel = "spring")
public interface StockMapper {


    ProduitDTO produitToProduitDTO(Produit produit);
    Produit produitDTOToProduit(ProduitDTO produitDTO);
    List<ProduitDTO> produitsToProduitsDTO(List<Produit> produits);
    List<Produit> produitsDTOToProduits(List<ProduitDTO> produitsDTO);




}
