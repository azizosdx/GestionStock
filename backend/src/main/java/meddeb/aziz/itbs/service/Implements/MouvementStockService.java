package meddeb.aziz.itbs.service.Implements;

import jakarta.persistence.EntityNotFoundException;
import meddeb.aziz.itbs.dto.MouvementStockDTO;
import meddeb.aziz.itbs.entity.Entrepot;
import meddeb.aziz.itbs.entity.MouvementStock;
import meddeb.aziz.itbs.entity.Produit;
import meddeb.aziz.itbs.entity.Stock;
import meddeb.aziz.itbs.entity.TypeMouvementStock;
import meddeb.aziz.itbs.mapper.MouvementStockMapper;
import meddeb.aziz.itbs.mapper.StockMapper;
import meddeb.aziz.itbs.repository.EntrepotRepository;
import meddeb.aziz.itbs.repository.MouvementStockRepository;
import meddeb.aziz.itbs.repository.ProduitRepository;
import meddeb.aziz.itbs.repository.StockRepository;
import meddeb.aziz.itbs.service.IMouvementStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MouvementStockService implements IMouvementStockService {

     @Autowired
     private ProduitRepository produitRepository;

     @Autowired
     private EntrepotRepository entrepotRepository;

     @Autowired
     private StockRepository stockRepository;

     @Autowired
     private MouvementStockRepository mouvementStockRepository;

     @Autowired
     private MouvementStockMapper mouvementStockMapper;

     public MouvementStockDTO createMouvementStock(MouvementStockDTO mouvementStockDTO) {
         Produit produit = produitRepository.findByNom(mouvementStockDTO.getProduit().getNom());
         Entrepot entrepot = entrepotRepository.findByNom(mouvementStockDTO.getEntrepot().getNom());

         if (produit == null) {
             throw new EntityNotFoundException("Produit not found");
         }

         if ( entrepot == null) {
             throw new EntityNotFoundException("Entrepot not found");
         }

         Stock stock = stockRepository.findByProduitAndEntrepot(produit, entrepot);

         if (stock == null) {
                // If stock does not exist, create a new stock entry
                stock = new Stock();
                stock.setProduit(produit);
                stock.setEntrepot(entrepot);
                stock.setQuantite(0); // Initialize quantity to 0
                stock.setSeuilAlerte(0); // Initialize alert threshold to 0
            } else if (stock.getQuantite() < mouvementStockDTO.getQuantite() && mouvementStockDTO.getType() == TypeMouvementStock.SORTIE) {
                throw new EntityNotFoundException("Insufficient stock quantity");
            }


         if (mouvementStockDTO.getType() == TypeMouvementStock.ENTREE) {
             stock.setQuantite(stock.getQuantite() + mouvementStockDTO.getQuantite());
         } else {
             stock.setQuantite(stock.getQuantite() - mouvementStockDTO.getQuantite());
         }

         stockRepository.save(stock);


         MouvementStock mouvementStock = mouvementStockMapper.mouvementStockDTOToEntity(mouvementStockDTO);

            mouvementStock.setProduit(produit);
            mouvementStock.setEntrepot(entrepot);
            mouvementStock.setType(mouvementStockDTO.getType());
            mouvementStock.setDate(new java.sql.Date(new Date().getTime()));


        mouvementStockRepository.save(mouvementStock);

        return mouvementStockMapper.entityToMouvementStockDTO(mouvementStock);

     }

    public MouvementStockDTO getMouvementStockById(Long id) {
        MouvementStock mouvementStock = mouvementStockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MouvementStock not found"));
        return mouvementStockMapper.entityToMouvementStockDTO(mouvementStock);
    }

    public void deleteMouvementStock(Long id) {
        MouvementStock mouvementStock = mouvementStockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MouvementStock not found"));
        mouvementStockRepository.delete(mouvementStock);
    }

    public List<MouvementStockDTO> getAllMouvementsStock() {
        List<MouvementStock> mouvementsStock = mouvementStockRepository.findAll();
        return mouvementStockMapper.entityToMouvementStockDTO(mouvementsStock);
    }

    public List<MouvementStockDTO> getMouvementsStockByProduitId(Long produitId) {
        List<MouvementStock> mouvementsStock = mouvementStockRepository.findByProduitId(produitId);
        return mouvementStockMapper.entityToMouvementStockDTO(mouvementsStock);
    }

    public List<MouvementStockDTO> getMouvementsStockByEntrepotId(Long entrepotId) {
        List<MouvementStock> mouvementsStock = mouvementStockRepository.findByEntrepotId(entrepotId);
        return mouvementStockMapper.entityToMouvementStockDTO(mouvementsStock);
    }

    public List<MouvementStockDTO> getMouvementsStockByProduitAndEntrepot(Long produitId, Long entrepotId) {
        List<MouvementStock> mouvementsStock = mouvementStockRepository.findByProduitIdAndEntrepotId(produitId, entrepotId);
        return mouvementStockMapper.entityToMouvementStockDTO(mouvementsStock);
    }

    public MouvementStockDTO updateMouvementStock(Long id, MouvementStockDTO mouvementStockDTO) {
        MouvementStock existingMouvementStock = mouvementStockRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MouvementStock not found"));

        Produit produit = produitRepository.findByNom(mouvementStockDTO.getProduit().getNom());
        Entrepot entrepot = entrepotRepository.findByNom(mouvementStockDTO.getEntrepot().getNom());

        Stock stock = stockRepository.findByProduitAndEntrepot(produit, entrepot);

        if (stock.getQuantite() < mouvementStockDTO.getQuantite() && mouvementStockDTO.getType() == TypeMouvementStock.SORTIE) {
            throw new EntityNotFoundException("Insufficient stock quantity");
        }

        if (mouvementStockDTO.getType() == TypeMouvementStock.ENTREE) {
            stock.setQuantite(stock.getQuantite() + mouvementStockDTO.getQuantite());
        } else {
            stock.setQuantite(stock.getQuantite() - mouvementStockDTO.getQuantite());
        }

        stockRepository.save(stock);

        mouvementStockMapper.mouvementStockDTOToEntity(mouvementStockDTO, existingMouvementStock);
        existingMouvementStock.setDate(new java.sql.Date(new Date().getTime()));
        MouvementStock updatedMouvementStock = mouvementStockRepository.save(existingMouvementStock);
        mouvementStockMapper.entityToMouvementStockDTO(updatedMouvementStock, mouvementStockDTO);
        return mouvementStockDTO;
    }




}
