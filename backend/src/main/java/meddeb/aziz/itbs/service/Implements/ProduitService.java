package meddeb.aziz.itbs.service.Implements;


import meddeb.aziz.itbs.dto.EntrepotDTO;
import meddeb.aziz.itbs.dto.ProduitDTO;
import meddeb.aziz.itbs.entity.Produit;
import meddeb.aziz.itbs.mapper.ProduitMapper;
import meddeb.aziz.itbs.repository.ProduitRepository;
import meddeb.aziz.itbs.service.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProduitService implements IProduitService {



    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitMapper produitMapper;




    public ProduitDTO createProduit(ProduitDTO produitDTO) throws Exception {
        Produit existingProduit = produitRepository.findByNom(produitDTO.getNom());

        if (existingProduit != null) {
            throw new Exception("Produit déjà existant");
        }


        Produit item = produitMapper.produitDTOToEntity(produitDTO);

        Produit savedProduit = produitRepository.save(item);

        return produitMapper.entityToProduitDTO(savedProduit);
    }


//    public ProduitDTO updateProduit(ProduitDTO produitDTO) throws Exception {
//        Produit existingProduit = produitRepository.findById(produitDTO.getId())
//                .orElseThrow(() -> new Exception("Produit non trouvé"));
//
//        Produit item = produitMapper.produitDTOToEntity(produitDTO);
//
//        Produit updatedProduit = produitRepository.save(item);
//
//        return produitMapper.entityToProduitDTO(updatedProduit);
//    }

    public void deleteProduit(Long id) throws Exception {
        Produit existingProduit = produitRepository.findById(id)
                .orElseThrow(() -> new Exception("Produit non trouvé"));

        produitRepository.delete(existingProduit);
    }

    public ProduitDTO getProduitById(Long id) throws Exception {
        Produit existingProduit = produitRepository.findById(id)
                .orElseThrow(() -> new Exception("Produit non trouvé"));

        return produitMapper.entityToProduitDTO(existingProduit);
    }

    public List<ProduitDTO> getAllProduits() throws Exception {
        List<Produit> produits = produitRepository.findAll();
        return produitMapper.entityToProduitDTO(produits);
    }



}
