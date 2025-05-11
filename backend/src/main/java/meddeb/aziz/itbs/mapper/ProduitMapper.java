package meddeb.aziz.itbs.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import meddeb.aziz.itbs.dto.ProduitDTO;
import meddeb.aziz.itbs.dto.ProduitDTO;
import meddeb.aziz.itbs.entity.Produit;
import meddeb.aziz.itbs.entity.Produit;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,componentModel = "spring")
public interface ProduitMapper {

    void produitDTOToEntity(ProduitDTO produitDto, @MappingTarget Produit produit);

    void entityToProduitDTO(Produit produit, @MappingTarget ProduitDTO produitDto);

    Produit produitDTOToEntity (ProduitDTO produitDTO);
	
    ProduitDTO entityToProduitDTO (Produit produit);
	
    List<ProduitDTO> entityToProduitDTO(List<Produit> produits);
	
}
