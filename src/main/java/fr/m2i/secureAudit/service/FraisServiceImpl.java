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

    // Constructeur avec un paramètre pour l'injection de dépendance
    public FraisServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Récupère un objet Frais par son ID
    @Override
    public Frais findById(int id_frais) {
        return fraisRepository.findById(id_frais).orElse(null);
    }

    // Récupère tous les objets Frais
    @Override
    public List<Frais> findAll() {
        return fraisRepository.findAll();
    }

    // Ajoute un nouvel objet Frais et l'associe à un audit et une catégorie
    @Override
    @Transactional
    public String addFrais(Frais frais, int id_audit, int id_categorie) {
        // Vérifie si tous les champs sont renseignés
        if (frais.getDate_debut_frais() == null || frais.getMontant() == 0 ||
                frais.getEst_rembourse() == null) {
            throw new IllegalArgumentException("All fields are required.");
        }
        // Récupère l'objet Audit correspondant à l'ID fourni
        Audit audit = entityManager.find(Audit.class, id_audit);
        if (audit == null) {
            throw new IllegalArgumentException("Invalid audit ID: " + id_audit);
        }
        // Récupère l'objet Categorie correspondant à l'ID fourni
        Categorie categorie = entityManager.find(Categorie.class, id_categorie);
        if (categorie == null) {
            throw new IllegalArgumentException("Invalid categorie ID: " + id_categorie);
        }
        // Associe l'objet Frais à l'objet Audit et à l'objet Categorie correspondants
        frais.setAudit(audit);
        frais.setCategorie(categorie);
        entityManager.persist(frais);
        return "frais added successfully";
    }

    // Met à jour un objet Frais existant
    @Override
    @Transactional
    public String update(int id_frais, Frais frais) {
        // Vérifie si tous les champs sont renseignés
        if (frais.getDate_debut_frais() == null || frais.getMontant() == 0 ||
                frais.getEst_rembourse() == null || frais.getAudit() == null
                || frais.getCategorie() == null) {
            throw new IllegalArgumentException("All fields are required.");
        }
        // Récupère l'objet Frais correspondant à l'ID fourni
        Frais existingFrais = entityManager.find(Frais.class, id_frais);
        if (existingFrais != null) {
            // Met à jour les champs de l'objet Frais existant avec les valeurs de l'objet Frais fourni
            existingFrais.setDate_debut_frais(frais.getDate_debut_frais());
            existingFrais.setMontant(frais.getMontant());
            existingFrais.setEst_rembourse(frais.getEst_rembourse());
            existingFrais.setAudit(frais.getAudit());
            existingFrais.setCategorie(frais.getCategorie());
            entityManager.merge(existingFrais);
        }
        return  "frais updated successfully";
    }

    // Supprime un objet Frais existant
// Cette méthode permet de supprimer un objet Frais en utilisant son identifiant.
// Elle vérifie d'abord si l'objet existe en utilisant la méthode existsById() du repository,
// puis supprime l'objet en utilisant la méthode deleteById() du même repository.

    @Override
    public boolean delete(int id_frais) {
        if (fraisRepository.existsById(id_frais)) {
            fraisRepository.deleteById(id_frais);
            return true;
        }
        return false;
    }

    // Recherche des frais par catégorie
    // Cette méthode permet de rechercher les frais en utilisant l'identifiant d'une catégorie.
    // Elle utilise une requête JPQL pour récupérer les objets Frais correspondants à la catégorie spécifiée.
    @Override
    @Transactional
    public List<Frais> findFraisByCategorie(int id_categorie) {
        String query = "SELECT f FROM Frais f WHERE f.categorie.id_categorie = :id_categorie";
        TypedQuery<Frais> jpqlQuery = entityManager.createQuery(query, Frais.class);
        jpqlQuery.setParameter("id_categorie", id_categorie);
        return jpqlQuery.getResultList();
    }

    // Recherche des frais par auditeur
    // Cette méthode permet de rechercher les frais en utilisant l'identifiant d'un auditeur.
    // Elle utilise une requête JPQL pour récupérer les objets Frais correspondants à l'auditeur spécifié.
    @Override
    @Transactional
    public List<Frais> findFraisByAuditeur(int id_auditeur) {
        String query = "SELECT f FROM Frais f JOIN f.audit a JOIN a.auditeur au WHERE au.id_auditeur = :id_auditeur";
        TypedQuery<Frais> jpqlQuery = entityManager.createQuery(query, Frais.class);
        jpqlQuery.setParameter("id_auditeur", id_auditeur);
        return jpqlQuery.getResultList();
    }

    // Recherche des frais par audit
    // Cette méthode permet de rechercher les frais en utilisant l'identifiant d'un audit.
    // Elle utilise une requête JPQL pour récupérer les objets Frais correspondants à l'audit spécifié.
    @Override
    @Transactional
    public List<Frais> findFraisByAudit(int id_audit) {
        String query = "SELECT f FROM Frais f WHERE f.audit.id_audit = :id_audit";
        TypedQuery<Frais> jpqlQuery = entityManager.createQuery(query, Frais.class);
        jpqlQuery.setParameter("id_audit", id_audit);
        return jpqlQuery.getResultList();
    }
}

