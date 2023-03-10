package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Audit;
import fr.m2i.secureAudit.model.Auditeur;
import fr.m2i.secureAudit.model.Industrie;
import fr.m2i.secureAudit.repository.AuditRepository;
import fr.m2i.secureAudit.repository.IndustrieRepository;
import fr.m2i.secureAudit.serviceInterfaces.AuditService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service //détecter automatiquement et instancier la classe, pour qu'elle puisse être utilisée dans le package Controller.
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditRepository auditRepository; // injection de l'objet Audit de l'interface AuditRepository dans une instance auditRepository

    @Autowired
    private IndustrieRepository industrieRepository; // injection de l'objet Industrie IndustrieRepository dans une instance industrieRepository

    private EntityManager entityManager; // l'objet EntityManager qui gère les entités dans la base de données

    public AuditServiceImpl(EntityManager entityManager) { // constructeur de la classe
        this.entityManager = entityManager;
    }

    @Override
    public Audit findById(int id_audit) { // retourne un Audit en fonction de son ID
        return auditRepository.findById(id_audit).orElse(null);
    }

    @Override
    public List<Audit> getAudit() { // retourne tous les audits
        return auditRepository.findAll();
    }

    @Override
    @Transactional
    public Audit addAudit(Audit audit, int id_industrie, int id_auditeur) { // ajoute un nouvel audit à la base de données
        if (audit.getDate_debut() == null || audit.getDuree() == 0 ||
                audit.getCout_jour() == 0 || audit.getCout_total() == 0) { // vérifie que tous les champs de l'audit sont remplis
            throw new IllegalArgumentException("All fields are required.");
        }
        Industrie industrie = entityManager.find(Industrie.class, id_industrie); // recherche l'industrie dans la base de données
        if (industrie == null) { // vérifie si l'industrie existe
            throw new IllegalArgumentException("Invalid industrie ID: " + id_industrie);
        }
        Auditeur auditeur = entityManager.find(Auditeur.class, id_auditeur); // recherche l'auditeur dans la base de données
        if (auditeur == null) { // vérifie si l'auditeur existe
            throw new IllegalArgumentException("Invalid auditeur ID: " + id_auditeur);
        }
        audit.setIndustrie(industrie); // associe l'audit à l'industrie
        audit.setAuditeur(auditeur); // associe l'audit à l'auditeur

        if (audit.getDuree() > 4 ) { // vérifie si la durée de l'audit est inférieure à 4 jours
            throw new IllegalArgumentException("duree doit etre inferieure a 4 jours");
        }
        entityManager.persist(audit); // ajouter l'audit dans la base de données
        return audit;
    }

    // La méthode update() permet de mettre à jour un audit dans la base de données.
    // Elle prend en entrée un objet Audit, ainsi que les identifiants de l'industrie et de l'auditeur liés à cet audit.
    @Override
    @Transactional
    public String update(Audit audit, int id_industrie, int id_auditeur) {
        // On vérifie que toutes les propriétés requises de l'objet Audit sont renseignées.
        if (audit.getDate_debut() == null || audit.getDuree() == 0 ||
                audit.getCout_jour() == 0 || audit.getCout_total() == 0
                || audit.getIndustrie() == null || audit.getAuditeur() == null) {
            // Si certaines propriétés sont manquantes, on lève une exception.
            throw new IllegalArgumentException("All fields are required.");
        }
        // On utilise l'EntityManager pour créer une requête SQL qui mettra à jour l'audit dans la base de données.
        entityManager.createNativeQuery("UPDATE audit " +
                        "SET date_debut = :date_debut, " +
                        "duree = :duree, " +
                        "cout_jour = :cout_jour, " +
                        "cout_total = :cout_total, " +
                        "id_industrie = :id_industrie, " +
                        "id_auditeur = :id_auditeur " +
                        "WHERE id_audit = :id_audit")
                .setParameter("date_debut", audit.getDate_debut())
                .setParameter("duree", audit.getDuree())
                .setParameter("cout_jour", audit.getCout_jour())
                .setParameter("cout_total", audit.getCout_total())
                .setParameter("id_industrie", id_industrie)
                .setParameter("id_auditeur", id_auditeur)
                .setParameter("id_audit", audit.getId_audit())
                .executeUpdate();
        // On retourne un message de confirmation.
        return "audit updated successfully";
    }

    // La méthode delete permet de supprimer un audit de la base de données.
    // Elle prend en entrée l'identifiant de l'audit à supprimer.
    @Override
    public boolean delete(int id_audit) {
        // On vérifie que l'audit à supprimer existe bien dans la base de données.
        if (auditRepository.existsById(id_audit)) {
            // Si c'est le cas, on utilise l'auditRepository pour supprimer l'audit de la base de données.
            auditRepository.deleteById(id_audit);
            // On retourne vrai pour indiquer que la suppression a été effectuée avec succès.
            return true;
        }
        // Si l'audit n'existe pas, on retourne faux.
        return false;
    }
    @Override
    public List<Audit> findByIndustrie(int id_industrie) {
        List<Audit> audits = auditRepository.findAll(); // récupère tous les audits
        List<Audit> result = new ArrayList<>(); // initialise une liste vide

        for (Audit audit : audits) { // pour chaque audit dans la liste des audits
            if (audit.getIndustrie().getId_industrie() == id_industrie) { // si l'ID de l'industrie correspond à l'ID passé en paramètre
                result.add(audit); // ajoute l'audit à la liste de résultats
            }
        }
        return result; // renvoie la liste de résultats
    }

    @Override
    public List<Audit> findByAuditeur(int id_auditeur) {
        List<Audit> audits = auditRepository.findAll(); // récupère tous les audits
        List<Audit> result = new ArrayList<>(); // initialise une liste vide

        for (Audit audit : audits) { // pour chaque audit dans la liste des audits
            if (audit.getAuditeur().getId_auditeur() == id_auditeur) { // si l'ID de l'auditeur correspond à l'ID passé en paramètre
                result.add(audit); // ajoute l'audit à la liste de résultats
            }
        }
        return result; // renvoie la liste de résultats
    }


}

