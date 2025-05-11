package meddeb.aziz.itbs.service.Implements;


import meddeb.aziz.itbs.dto.StockDTO;
import meddeb.aziz.itbs.entity.Entrepot;
import meddeb.aziz.itbs.entity.Produit;
import meddeb.aziz.itbs.entity.Stock;
import meddeb.aziz.itbs.mapper.StockMapper;
import meddeb.aziz.itbs.repository.EntrepotRepository;
import meddeb.aziz.itbs.repository.ProduitRepository;
import meddeb.aziz.itbs.repository.StockRepository;
import meddeb.aziz.itbs.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService implements IStockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private EntrepotRepository entrepotRepository;

    @Autowired
    private StockMapper stockMapper;


   public StockDTO createStock(StockDTO stockDTO) throws Exception {

       Produit produit = produitRepository.findById(stockDTO.getProduit().getId())
               .orElseThrow(() -> new Exception("Produit non trouvé"));
       Entrepot entrepot = entrepotRepository.findById(stockDTO.getEntrepot().getId())
               .orElseThrow(() -> new Exception("Entrepôt non trouvé"));

       Stock stock = new Stock();
       stock.setProduit(produit);
       stock.setEntrepot(entrepot);
       stock.setQuantite(stockDTO.getQuantite());
       stock.setSeuilAlerte(stockDTO.getSeuilAlerte());

       stock = stockRepository.save(stock);

       return stockMapper.stockToStockDTO(stock);
    }

public void deleteStock(Long id) throws Exception {
    Stock existingStock = stockRepository.findById(id)
            .orElseThrow(() -> new Exception("Stock non trouvé"));

    stockRepository.delete(existingStock);
}

public StockDTO getStockById(Long id) throws Exception {
    Stock existingStock = stockRepository.findById(id)
            .orElseThrow(() -> new Exception("Stock non trouvé"));

    return stockMapper.stockToStockDTO(existingStock);
}

public List<StockDTO> getAllStocks() {
    List<Stock> stocks = stockRepository.findAll();
    return stockMapper.stocksToStocksDTO(stocks);
}

// Update stock
public StockDTO updateStock(Long id, StockDTO stockDTO) throws Exception {
    Stock existingStock = stockRepository.findById(id)
            .orElseThrow(() -> new Exception("Stock non trouvé"));

    stockDTO.setId(id);
    stockMapper.stockDTOToEntity(stockDTO,existingStock);

    Stock updatedStock = stockRepository.save(existingStock);

    return stockMapper.stockToStockDTO(updatedStock);
}





}
