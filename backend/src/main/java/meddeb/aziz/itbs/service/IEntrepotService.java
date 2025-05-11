package meddeb.aziz.itbs.service;

import meddeb.aziz.itbs.dto.EntrepotDTO;
import meddeb.aziz.itbs.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IEntrepotService {

    public EntrepotDTO createEntrepot(EntrepotDTO entrepotDTO) throws Exception;
    public EntrepotDTO updateEntrepot(Long id, EntrepotDTO entrepotDTO) throws Exception;
    public EntrepotDTO getEntrepotById(Long id) throws Exception;
    public void deleteEntrepot(Long id) throws Exception;
    public List<EntrepotDTO> getAllEntrepots() throws Exception;



}
