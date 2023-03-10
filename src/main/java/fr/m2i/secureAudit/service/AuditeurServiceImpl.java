package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Auditeur;
import fr.m2i.secureAudit.repository.AuditeurRepository;
import fr.m2i.secureAudit.serviceInterfaces.AuditeurService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditeurServiceImpl implements AuditeurService {

    @Autowired
    private AuditeurRepository auditeurRepository; // Injecte l'interface AuditeurRepository pour accéder à la couche de persistance

    private EntityManager entityManager; // EntityManager permet l'exécution de requêtes personnalisées

    public AuditeurServiceImpl(EntityManager entityManager) { // Constructeur qui permet d'injecter un EntityManager
        this.entityManager = entityManager;
    }

    @Override
    public List<Auditeur> getAuditeurs() { // Récupère tous les auditeurs
        return auditeurRepository.findAll();
    }

    @Override
    @Transactional // Permet de gérer la transaction à travers le service
    public Auditeur rechercheById(int id_auditeur) { // Recherche un auditeur par son id
        Query query = entityManager.createNativeQuery("SELECT * FROM auditeur a WHERE a.id_auditeur = :id_auditeur", Auditeur.class); // Crée une requête SQL personnalisée qui sera exécutée par l'EntityManager
        query.setParameter("id_auditeur", id_auditeur); // Ajoute le paramètre 'id_auditeur' à la requête
        List<Auditeur> result = query.getResultList(); // Exécute la requête et retourne une liste de résultats
        if (!result.isEmpty()) { // Si la liste de résultats n'est pas vide
            return result.get(0); // Retourne le premier élément de la liste
        }
        return null; // Retourne null si la liste est vide
    }

    @Override
    public Auditeur createAuditeur(Auditeur auditeur) { // Crée un nouvel auditeur
        if (auditeur.getCivilite() == null || auditeur.getNom() == null ||
                auditeur.getPrenom() == null) { // Vérifie que tous les champs obligatoires sont remplis
            throw new IllegalArgumentException("All fields (civilite, nom, prenom) are required."); // Lève une exception si un champ est manquant
        }
        if (auditeur.getCivilite().length() > 3) { // Vérifie que la civilite ne dépasse pas 3 caractères
            throw new IllegalArgumentException("Civilite must be no more than 3 characters long."); // Lève une exception si la civilite est trop longue
        }
        return auditeurRepository.save(auditeur); // Enregistre l'auditeur dans la base de données et le retourne
    }

    @Override
    public boolean update(int id_auditeur, Auditeur auditeur) { // Met à jour un auditeur existant
        if (auditeur.getCivilite() == null || auditeur.getNom() == null ||
                auditeur.getPrenom() == null) { // Vérifie que tous les champs obligatoires sont remplis
            throw new IllegalArgumentException("All fields (civilite, nom, prenom) are required."); // Lève une exception si un champ est manquant
        }
        if (auditeur.getCivilite().length() > 3) { // Vérifie que la civilite ne dépasse pas 3 caractères
            throw new IllegalArgumentException("Civilite must be no more than 3 characters long."); // Lève une exception si la civilite est trop longue
        }
        Auditeur existingAuditeur = auditeurRepository.findById(id_auditeur).orElse(null); // Récupère l'auditeur à mettre à jour depuis la base de données
        if (existingAuditeur != null) {
            existingAuditeur.setCivilite(auditeur.getCivilite());
            existingAuditeur.setNom(auditeur.getNom());
            existingAuditeur.setPrenom(auditeur.getPrenom());
            auditeurRepository.save(existingAuditeur);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(int id_auditeur) {
        if (auditeurRepository.existsById(id_auditeur)) {
            auditeurRepository.deleteById(id_auditeur);
            return true;
        }
        return false;
    }
}

