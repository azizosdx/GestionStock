package meddeb.aziz.itbs.controller;

import meddeb.aziz.itbs.dto.ProduitDTO;
import meddeb.aziz.itbs.service.Implements.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/produit")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

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



}
