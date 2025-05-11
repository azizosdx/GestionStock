package meddeb.aziz.itbs.mapper;

import meddeb.aziz.itbs.dto.StockDTO;
import meddeb.aziz.itbs.dto.StockDTO;
import meddeb.aziz.itbs.entity.Stock;
import meddeb.aziz.itbs.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,componentModel = "spring")
public interface StockMapper {

    void stockDTOToEntity(StockDTO stockDto, @MappingTarget Stock stock);
    StockDTO stockToStockDTO(Stock stock);
    Stock stockDTOToStock(StockDTO stockDTO);
    List<StockDTO> stocksToStocksDTO(List<Stock> stocks);
    List<Stock> stocksDTOToStocks(List<StockDTO> stockDTOs);




}
