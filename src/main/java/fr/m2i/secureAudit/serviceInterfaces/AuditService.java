package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Audit;
import java.util.List;

// préparation des méthodes CRUD pour Audit
public interface AuditService {

    Audit findById(int id_audit);

    List<Audit> getAudit();

    Audit addAudit(Audit audit, int id_industrie, int id_auditeur);

    String update(Audit audit, int new_id_industrie, int new_id_auditeur);

    boolean delete(int id_audit);

    List<Audit> findByIndustrie(int id_industrie);

    List<Audit> findByAuditeur(int id_auditeur);
}

