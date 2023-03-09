package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Industrie;
import fr.m2i.secureAudit.repository.IndustrieRepository;
import fr.m2i.secureAudit.serviceInterfaces.IndustrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndustrieServiceImpl implements IndustrieService {

    private final IndustrieRepository industrieRepository;

    @Autowired
    public IndustrieServiceImpl(IndustrieRepository industrieRepository) {
        this.industrieRepository = industrieRepository;
    }

    @Override
    public Industrie findById(int id_industrie) {
        return industrieRepository.findById(id_industrie).orElse(null);
    }

    @Override
    public List<Industrie> findAll() {
        return industrieRepository.findAll();
    }

    public void save(Industrie industrie) {
        if (industrie.getRaison_sociale() == null) {
            throw new IllegalArgumentException("All fields are required.");
        }
        Long siret = industrie.getNumero_siret();
        String siretString = String.valueOf(siret);
        if (siretString.length() == 14) {
            industrieRepository.save(industrie);
        } else {
            throw new IllegalArgumentException("Check the SIRET number : " + siretString);
        }
    }

    @Override
    public void update(int id_industrie, Industrie industrie) {
        if (industrie.getRaison_sociale() == null) {
            throw new IllegalArgumentException("All fields are required.");
        }
        Industrie existingIndustrie = industrieRepository.findById(id_industrie).orElse(null);
        if (existingIndustrie != null) {
            Long siret = industrie.getNumero_siret();
            String siretString = String.valueOf(siret);
            if (siretString.length() == 14) {
                existingIndustrie.setNumero_siret(siret);
                existingIndustrie.setRaison_sociale(industrie.getRaison_sociale());
                industrieRepository.save(existingIndustrie);
            } else {
                throw new IllegalArgumentException("Check the SIRET number : " + siretString);
            }
        }
    }

    @Override
    public boolean delete(int id_industrie) {
        if (industrieRepository.existsById(id_industrie)) {
            industrieRepository.deleteById(id_industrie);
            return true;
        }
        return false;
    }
}

