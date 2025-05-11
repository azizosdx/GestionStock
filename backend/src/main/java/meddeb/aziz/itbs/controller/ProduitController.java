package meddeb.aziz.itbs.controller;

import meddeb.aziz.itbs.dto.ProduitDTO;
import meddeb.aziz.itbs.service.IProduitService;
import meddeb.aziz.itbs.service.Implements.ProduitService;
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
@RequestMapping("/api/produit")
@CrossOrigin(origins = "http://localhost:4200")
public class ProduitController {

    @Autowired
    private IProduitService produitService;

    @GetMapping
    public ResponseEntity<List<ProduitDTO>> getAllProduct() throws Exception {
        List<ProduitDTO> products = produitService.getAllProduits();
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProduitDTO> getProductById(@PathVariable Long id) throws Exception {
        ProduitDTO product = produitService.getProduitById(id);
        return ResponseEntity.ok(product);
    }


    @PostMapping
    public ResponseEntity<ProduitDTO> createProduct(@RequestBody ProduitDTO produitDTO) throws Exception {
        ProduitDTO createdProduct = produitService.createProduit(produitDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitDTO> updateProduct(@PathVariable Long id, @RequestBody ProduitDTO produitDTO) throws Exception {
        ProduitDTO updatedProduct = produitService.updateProduit(id,produitDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws Exception {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

}
