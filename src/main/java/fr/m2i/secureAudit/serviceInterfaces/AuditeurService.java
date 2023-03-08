package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Auditeur;
import java.util.List;
import java.util.Optional;

public interface AuditeurService {

    Auditeur rechercheById(int id_auditeur);

    List<Auditeur> getAuditeurs();

    Auditeur createAuditeur(Auditeur auditeur);

    void update(int id_auditeur, Auditeur auditeur);

    String deleteById(int id_auditeur);
}

