package meddeb.aziz.itbs.controller;


import meddeb.aziz.itbs.dto.MouvementStockDTO;
import meddeb.aziz.itbs.entity.MouvementStock;
import meddeb.aziz.itbs.service.IMouvementStockService;
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
@RequestMapping("/api/mouvement-stock")
@CrossOrigin(origins = "http://localhost:4200")
public class MouvementStockController {

    @Autowired
    private IMouvementStockService mouvementStockService;

    @GetMapping
    public ResponseEntity<List<MouvementStockDTO>> getAllMouvementStock() throws Exception {
        List<MouvementStockDTO> mouvements = mouvementStockService.getAllMouvementsStock();
        return ResponseEntity.ok(mouvements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MouvementStockDTO> getMouvementStockById(@PathVariable Long id) throws Exception {
        MouvementStockDTO mouvement = mouvementStockService.getMouvementStockById(id);
        return ResponseEntity.ok(mouvement);
    }
    @GetMapping("/produit/{id}")
    public ResponseEntity<List<MouvementStockDTO>> getMouvementStockByProduitId(@PathVariable Long id) throws Exception {
        List<MouvementStockDTO> mouvements = mouvementStockService.getMouvementsStockByProduitId(id);
        return ResponseEntity.ok(mouvements);
    }
    @GetMapping("/entrepot/{id}")
    public ResponseEntity<List<MouvementStockDTO>> getMouvementStockByEntrepotId(@PathVariable Long id) throws Exception {
        List<MouvementStockDTO> mouvements = mouvementStockService.getMouvementsStockByEntrepotId(id);
        return ResponseEntity.ok(mouvements);
    }

    @PostMapping
    public ResponseEntity<MouvementStockDTO> createMouvementStock(@RequestBody MouvementStockDTO mouvementStockDTO) throws Exception {
        MouvementStockDTO createdMouvement = mouvementStockService.createMouvementStock(mouvementStockDTO);
        return ResponseEntity.ok(createdMouvement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MouvementStockDTO> updateMouvementStock(@PathVariable Long id, @RequestBody MouvementStockDTO mouvementStockDTO) throws Exception {
        MouvementStockDTO updatedMouvement = mouvementStockService.updateMouvementStock(id, mouvementStockDTO);
        return ResponseEntity.ok(updatedMouvement);
    }

    @GetMapping("/produit/{id}/entrepot/{idEntrepot}")
    public ResponseEntity<List<MouvementStockDTO>> getMouvementStockByProduitIdAndEntrepotId(@PathVariable Long id, @PathVariable Long idEntrepot) throws Exception {
        List<MouvementStockDTO> mouvements = mouvementStockService.getMouvementsStockByProduitAndEntrepot(id, idEntrepot);
        return ResponseEntity.ok(mouvements);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMouvementStock(@PathVariable Long id) throws Exception {
        mouvementStockService.deleteMouvementStock(id);
        return ResponseEntity.noContent().build();
    }
}
