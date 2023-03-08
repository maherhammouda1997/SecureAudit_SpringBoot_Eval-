package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Frais;
import fr.m2i.secureAudit.repository.FraisRepository;
import fr.m2i.secureAudit.serviceInterfaces.FraisService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraisServiceImpl implements FraisService {

    @Autowired
    private FraisRepository fraisRepository;

    private EntityManager entityManager;

    public FraisServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Frais findById(int id_frais) {
        return fraisRepository.findById(id_frais).orElse(null);
    }

    @Override
    public List<Frais> findAll() {
        return fraisRepository.findAll();
    }

    @Override
    public void save(Frais frais) {
        fraisRepository.save(frais);
    }

    @Override
    @Transactional
    public void update(int id_frais, Frais frais) {
        Frais existingFrais = entityManager.find(Frais.class, id_frais);
        if (existingFrais != null) {
            existingFrais.setDate_debut_frais(frais.getDate_debut_frais());
            existingFrais.setMontant(frais.getMontant());
            existingFrais.setEst_rembourse(frais.getEst_rembourse());
            existingFrais.setAudit(frais.getAudit());
            existingFrais.setCategorie(frais.getCategorie());
            existingFrais.setAuditeur(frais.getAuditeur());
            entityManager.merge(existingFrais);
        }
    }

    @Override
    public void delete(int id_frais) {
        fraisRepository.deleteById(id_frais);
    }
}

