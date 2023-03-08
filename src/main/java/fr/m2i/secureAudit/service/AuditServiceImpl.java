package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Audit;
import fr.m2i.secureAudit.repository.AuditRepository;
import fr.m2i.secureAudit.serviceInterfaces.AuditService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditRepository auditRepository;

    private EntityManager entityManager;

    public AuditServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Audit findById(int id_audit) {
        return auditRepository.findById(id_audit).orElse(null);
    }

    @Override
    public List<Audit> getAudit() {
        return auditRepository.findAll();
    }

    @Override
    public void save(Audit audit) {
        auditRepository.save(audit);
    }

    @Override
    @Transactional
    public void update(int id_audit, Audit audit) {
        Audit existingAudit = entityManager.find(Audit.class, id_audit);
        if (existingAudit != null) {
            existingAudit.setDate_debut(audit.getDate_debut());
            existingAudit.setDuree(audit.getDuree());
            existingAudit.setCout_jour(audit.getCout_jour());
            existingAudit.setCout_total(audit.getCout_total());
            existingAudit.setIndustrie(audit.getIndustrie());
            existingAudit.setAuditeur(audit.getAuditeur());
            entityManager.merge(existingAudit);
        }
    }

    @Override
    public void delete(int id_audit) {
        auditRepository.deleteById(id_audit);
    }
}

