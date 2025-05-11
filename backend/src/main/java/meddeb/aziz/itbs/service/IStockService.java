package meddeb.aziz.itbs.service;

import meddeb.aziz.itbs.dto.StockDTO;

import java.util.List;

public interface IStockService {
    public StockDTO createStock(StockDTO stockDTO) throws Exception;
    public StockDTO updateStock(Long id, StockDTO stockDTO) throws Exception;
    public StockDTO getStockById(Long id) throws Exception;
    public void deleteStock(Long id) throws Exception;
    public List<StockDTO> getAllStocks() throws Exception;

}
