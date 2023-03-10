package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.*;
import fr.m2i.secureAudit.repository.FraisRepository;
import fr.m2i.secureAudit.serviceInterfaces.FraisService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
        if (frais.getDate_debut_frais() == null || frais.getMontant() == 0 ||
                frais.getEst_rembourse() == null) {
            throw new IllegalArgumentException("All fields are required.");
        }
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
        if (frais.getDate_debut_frais() == null || frais.getMontant() == 0 ||
                frais.getEst_rembourse() == null || frais.getAudit() == null
                || frais.getCategorie() == null) {
            throw new IllegalArgumentException("All fields are required.");
        }
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

//    @Override
//    @Transactional
//    public List<Frais> findFraisByCategorie(int id_categorie) {
//        String query = "SELECT * FROM frais WHERE id_categorie = :id_categorie";
//        Query nativeQuery = entityManager.createNativeQuery(query, Frais.class);
//        nativeQuery.setParameter("id_categorie", id_categorie);
//        return nativeQuery.getResultList();
//    }
//
//    @Override
//    @Transactional
//    public List<Frais> findFraisByAuditeur(int id_auditeur) {
//        String query = "SELECT f.* FROM frais f INNER JOIN audit a ON f.id_audit = a.id_audit INNER JOIN auditeur au ON a.id_auditeur = au.id_auditeur WHERE au.id_auditeur = :id_auditeur";
//        Query nativeQuery = entityManager.createNativeQuery(query, Frais.class);
//        nativeQuery.setParameter("id_auditeur", id_auditeur);
//        return nativeQuery.getResultList();
//    }
//
//    @Override
//    @Transactional
//    public List<Frais> findFraisByAudit(int id_audit) {
//        String query = "SELECT * FROM frais WHERE id_audit = :id_audit";
//        Query nativeQuery = entityManager.createNativeQuery(query, Frais.class);
//        nativeQuery.setParameter("id_audit", id_audit);
//        return nativeQuery.getResultList();
//    }

    @Override
    @Transactional
    public List<Frais> findFraisByCategorie(int id_categorie) {
        String query = "SELECT f FROM Frais f WHERE f.categorie.id_categorie = :id_categorie";
        TypedQuery<Frais> jpqlQuery = entityManager.createQuery(query, Frais.class);
        jpqlQuery.setParameter("id_categorie", id_categorie);
        return jpqlQuery.getResultList();
    }
    @Override
    @Transactional
    public List<Frais> findFraisByAuditeur(int id_auditeur) {
        String query = "SELECT f FROM Frais f JOIN f.audit a JOIN a.auditeur au WHERE au.id_auditeur = :id_auditeur";
        TypedQuery<Frais> jpqlQuery = entityManager.createQuery(query, Frais.class);
        jpqlQuery.setParameter("id_auditeur", id_auditeur);
        return jpqlQuery.getResultList();
    }
    @Override
    @Transactional
    public List<Frais> findFraisByAudit(int id_audit) {
        String query = "SELECT f FROM Frais f WHERE f.audit.id_audit = :id_audit";
        TypedQuery<Frais> jpqlQuery = entityManager.createQuery(query, Frais.class);
        jpqlQuery.setParameter("id_audit", id_audit);
        return jpqlQuery.getResultList();
    }

}

