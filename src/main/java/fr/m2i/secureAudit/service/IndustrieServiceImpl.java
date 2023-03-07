package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Industrie;
import fr.m2i.secureAudit.repository.IndustrieRepository;
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

    @Override
    public void save(Industrie industrie) {
        industrieRepository.save(industrie);
    }

    @Override
    public void update(int id_industrie, Industrie industrie) {
        Industrie existingIndustrie = industrieRepository.findById(id_industrie).orElse(null);
        if (existingIndustrie != null) {
            existingIndustrie.setNumeroSiret(industrie.getNumeroSiret());
            existingIndustrie.setRaisonSociale(industrie.getRaisonSociale());
            existingIndustrie.setAuditeur(industrie.getAuditeur());
            industrieRepository.save(existingIndustrie);
        }
    }

    @Override
    public void delete(int id_industrie) {
        industrieRepository.deleteById(id_industrie);
    }
}

