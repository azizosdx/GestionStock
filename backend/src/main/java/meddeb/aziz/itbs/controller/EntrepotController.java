package meddeb.aziz.itbs.controller;

import meddeb.aziz.itbs.dto.EntrepotDTO;
import meddeb.aziz.itbs.service.IEntrepotService;
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
@RequestMapping("/api/entrepot")
@CrossOrigin(origins = "http://localhost:4200")
public class EntrepotController {

    @Autowired
    private IEntrepotService entrepotService;

    @GetMapping
    public ResponseEntity<List<EntrepotDTO>> getAllEntrepots() throws Exception {
        List<EntrepotDTO> entrepots = entrepotService.getAllEntrepots();
        return ResponseEntity.ok(entrepots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrepotDTO> getEntrepotById(@PathVariable Long id) throws Exception {
        EntrepotDTO entrepot = entrepotService.getEntrepotById(id);
        return ResponseEntity.ok(entrepot);
    }

    @PostMapping
    public ResponseEntity <EntrepotDTO> createEntrepot(@RequestBody EntrepotDTO entrepotDTO) throws Exception {
        EntrepotDTO createdEntrepot = entrepotService.createEntrepot(entrepotDTO);
        return ResponseEntity.ok(createdEntrepot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrepotDTO> updateEntrepot(@PathVariable Long id, @RequestBody EntrepotDTO entrepotDTO) throws Exception {
        EntrepotDTO updatedEntrepot = entrepotService.updateEntrepot(id, entrepotDTO);
        return ResponseEntity.ok(updatedEntrepot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrepot(@PathVariable Long id) throws Exception {
        entrepotService.deleteEntrepot(id);
        return ResponseEntity.noContent().build();
    }



}
