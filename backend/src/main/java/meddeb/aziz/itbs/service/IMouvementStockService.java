package meddeb.aziz.itbs.service;

import meddeb.aziz.itbs.dto.MouvementStockDTO;

import java.util.List;

public interface IMouvementStockService {

    public MouvementStockDTO createMouvementStock(MouvementStockDTO mouvementStockDTO);
    public MouvementStockDTO updateMouvementStock(Long id, MouvementStockDTO mouvementStockDTO);
    public MouvementStockDTO getMouvementStockById(Long id);
    public void deleteMouvementStock(Long id);
    public List<MouvementStockDTO> getAllMouvementsStock();
    public List<MouvementStockDTO> getMouvementsStockByProduitId(Long produitId);
    public List<MouvementStockDTO> getMouvementsStockByEntrepotId(Long entrepotId);
    public List<MouvementStockDTO> getMouvementsStockByProduitAndEntrepot(Long produitId, Long entrepotId);
}
