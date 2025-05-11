package meddeb.aziz.itbs.service;

import meddeb.aziz.itbs.dto.ProduitDTO;

import java.util.List;

public interface IProduitService {

    public ProduitDTO createProduit(ProduitDTO produitDTO) throws Exception;

    public ProduitDTO updateProduit(Long id ,ProduitDTO produitDTO) throws Exception;

    public ProduitDTO getProduitById(Long id) throws Exception;

    public void deleteProduit(Long id) throws Exception;

    public List<ProduitDTO> getAllProduits() throws Exception;

}
