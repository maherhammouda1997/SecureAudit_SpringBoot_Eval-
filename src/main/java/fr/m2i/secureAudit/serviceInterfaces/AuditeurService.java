package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Auditeur;
import jakarta.transaction.Transactional;

import java.util.List;

public interface AuditeurService {

    Auditeur rechercheById(int id_auditeur);

    List<Auditeur> getAuditeurs();

    Auditeur createAuditeur(Auditeur auditeur);

    @Transactional
    abstract void update(int id_auditeur, Auditeur auditeur);

    void deleteById(int id_auditeur);
}

