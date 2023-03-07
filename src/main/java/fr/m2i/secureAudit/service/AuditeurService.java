package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Auditeur;
import java.util.List;
import java.util.Optional;

public interface AuditeurService {

    Auditeur findById(int id_auditeur);

    List<Auditeur> getAuditeurs();

    Auditeur save(Auditeur auditeur);

    void update(int id_auditeur, Auditeur auditeur);

    void deleteById(int id_auditeur);
}

