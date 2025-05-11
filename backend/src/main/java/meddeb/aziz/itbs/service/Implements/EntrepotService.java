package meddeb.aziz.itbs.service.Implements;

import meddeb.aziz.itbs.dto.EntrepotDTO;
import meddeb.aziz.itbs.entity.Entrepot;
import meddeb.aziz.itbs.mapper.EntrepotMapper;
import meddeb.aziz.itbs.repository.EntrepotRepository;
import meddeb.aziz.itbs.service.IEntrepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepotService implements IEntrepotService {


    @Autowired
    private EntrepotRepository entrepotRepository;

    @Autowired
    private EntrepotMapper entrepotMapper;


    public EntrepotDTO createEntrepot(EntrepotDTO entrepotDTO) throws Exception {
        Entrepot existingEntrepot = entrepotRepository.findByNom(entrepotDTO.getNom());

        if (existingEntrepot != null) {
            throw new Exception("Entrepôt déjà existant");
        }

        Entrepot item = entrepotMapper.entrepotDTOToEntity(entrepotDTO);

        Entrepot savedEntrepot = entrepotRepository.save(item);

        return entrepotMapper.entityToEntrepotDTO(savedEntrepot);
    }

    public EntrepotDTO updateEntrepot(Long id, EntrepotDTO entrepotDTO) throws Exception {
        Entrepot existingEntrepot = entrepotRepository.findById(id)
                .orElseThrow(() -> new Exception("Entrepôt non trouvé"));

        entrepotMapper.entrepotDTOToEntity(entrepotDTO,existingEntrepot);

        Entrepot updatedEntrepot = entrepotRepository.save(existingEntrepot);

        entrepotMapper.entityToEntrepotDTO(updatedEntrepot,entrepotDTO);

        return entrepotDTO;
    }

    public void deleteEntrepot(Long id) throws Exception {
        Entrepot existingEntrepot = entrepotRepository.findById(id)
                .orElseThrow(() -> new Exception("Entrepôt non trouvé"));

        entrepotRepository.delete(existingEntrepot);
    }

    public EntrepotDTO getEntrepotById(Long id) throws Exception {
        Entrepot existingEntrepot = entrepotRepository.findById(id)
                .orElseThrow(() -> new Exception("Entrepôt non trouvé"));

        return entrepotMapper.entityToEntrepotDTO(existingEntrepot);
    }
    public List<EntrepotDTO> getAllEntrepots() {
        List<Entrepot> entrepots = entrepotRepository.findAll();
        return entrepotMapper.entityToEntrepotDTO(entrepots);
    }

}
