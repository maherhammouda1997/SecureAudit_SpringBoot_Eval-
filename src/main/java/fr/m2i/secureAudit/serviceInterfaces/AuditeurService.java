package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Auditeur;

import java.util.List;

// préparation des méthodes CRUD pour Auditeur
public interface AuditeurService {

    Auditeur rechercheById(int id_auditeur);

    List<Auditeur> getAuditeurs();

    Auditeur createAuditeur(Auditeur auditeur);

    boolean update(int id_auditeur, Auditeur auditeur);

    boolean deleteById(int id_auditeur);
}

