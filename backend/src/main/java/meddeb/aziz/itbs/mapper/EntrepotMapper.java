package meddeb.aziz.itbs.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import meddeb.aziz.itbs.dto.EntrepotDTO;
import meddeb.aziz.itbs.entity.Entrepot;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,componentModel = "spring")
public interface EntrepotMapper {

	void entrepotDTOToEntity(EntrepotDTO entrepotDto, @MappingTarget Entrepot entrepot);

	void entityToEntrepotDTO(Entrepot entrepot, @MappingTarget EntrepotDTO entrepotDto);

	Entrepot entrepotDTOToEntity (EntrepotDTO entrepotDTO);

	EntrepotDTO entityToEntrepotDTO (Entrepot entrepot);
	
    List<EntrepotDTO> entityToEntrepotDTO(List<Entrepot> entrepots);

	
}
