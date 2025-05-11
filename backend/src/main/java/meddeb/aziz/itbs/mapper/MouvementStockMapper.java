package meddeb.aziz.itbs.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import meddeb.aziz.itbs.dto.MouvementStockDTO;
import meddeb.aziz.itbs.dto.MouvementStockDTO;
import meddeb.aziz.itbs.entity.MouvementStock;
import meddeb.aziz.itbs.entity.MouvementStock;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,componentModel = "spring")
public interface MouvementStockMapper {

    void mouvementStockDTOToEntity(MouvementStockDTO mouvementStockDto, @MappingTarget MouvementStock mouvementStock);

    void entityToMouvementStockDTO(MouvementStock mouvementStock, @MappingTarget MouvementStockDTO mouvementStockDto);

    MouvementStock mouvementStockDTOToEntity (MouvementStockDTO mouvementStockDTO);
	
    MouvementStockDTO entityToMouvementStockDTO (MouvementStock mouvementStock);
	
    List<MouvementStockDTO> entityToMouvementStockDTO(List<MouvementStock> mouvementStocks);

	
}
