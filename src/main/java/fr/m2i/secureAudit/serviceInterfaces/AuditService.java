package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Audit;
import java.util.List;

public interface AuditService {

    Audit findById(int id_audit);

    List<Audit> getAudit();

    void save(Audit audit);

    void update(int id_audit, Audit audit);

    void delete(int id_audit);
}

