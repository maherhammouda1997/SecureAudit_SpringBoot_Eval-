package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.*;
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
    @Transactional
    public String addFrais(Frais frais, int id_audit, int id_categorie) {
        Audit audit = entityManager.find(Audit.class, id_audit);
        if (audit == null) {
            throw new IllegalArgumentException("Invalid audit ID: " + id_audit);
        }

        Categorie categorie = entityManager.find(Categorie.class, id_categorie);
        if (categorie == null) {
            throw new IllegalArgumentException("Invalid categorie ID: " + id_categorie);
        }
        frais.setAudit(audit);
        frais.setCategorie(categorie);
        entityManager.persist(frais);
        return "frais added successfully";
    }

    @Override
    @Transactional
    public String update(int id_frais, Frais frais) {
        Frais existingFrais = entityManager.find(Frais.class, id_frais);
        if (existingFrais != null) {
            existingFrais.setDate_debut_frais(frais.getDate_debut_frais());
            existingFrais.setMontant(frais.getMontant());
            existingFrais.setEst_rembourse(frais.getEst_rembourse());
            existingFrais.setAudit(frais.getAudit());
            existingFrais.setCategorie(frais.getCategorie());
            entityManager.merge(existingFrais);
        }
        return  "frais updated successfully";
    }

    @Override
    public boolean delete(int id_frais) {
        if (fraisRepository.existsById(id_frais)) {
            fraisRepository.deleteById(id_frais);
            return true;
        }
        return false;
    }
}

