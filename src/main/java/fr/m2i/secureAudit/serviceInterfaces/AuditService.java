package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Audit;
import java.util.List;

public interface AuditService {

    Audit findById(int id_audit);

    List<Audit> getAudit();

    String addAudit(Audit audit, int id_industrie, int id_auditeur);

    String update(Audit audit, int new_id_industrie, int new_id_auditeur);

    void delete(int id_audit);
}

