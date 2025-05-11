package meddeb.aziz.itbs.controller;

import meddeb.aziz.itbs.dto.StockDTO;
import meddeb.aziz.itbs.service.IStockService;
import meddeb.aziz.itbs.service.Implements.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "http://localhost:4200")
public class StockController {

    @Autowired
    private IStockService stockService;

    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStocks() throws Exception {
        List<StockDTO> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable Long id) throws Exception {
        StockDTO stock = stockService.getStockById(id);
        return ResponseEntity.ok(stock);
    }
    @PostMapping
    public ResponseEntity<StockDTO> createStock(@RequestBody StockDTO stockDTO) throws Exception {
        StockDTO createdStock = stockService.createStock(stockDTO);
        return ResponseEntity.ok(createdStock);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable Long id, @RequestBody StockDTO stockDTO) throws Exception {
        StockDTO updatedStock = stockService.updateStock(id, stockDTO);
        return ResponseEntity.ok(updatedStock);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) throws Exception {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
