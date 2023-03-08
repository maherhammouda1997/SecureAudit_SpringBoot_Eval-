package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Audit;
import fr.m2i.secureAudit.model.Auditeur;
import fr.m2i.secureAudit.model.Industrie;
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

//    @Override
//    public void save(Audit audit) {
//        auditRepository.save(audit);
//    }

    @Override
    @Transactional
    public String addAudit(Audit audit, int id_industrie, int id_auditeur) {
        Industrie industrie = entityManager.find(Industrie.class, id_industrie);
        if (industrie == null) {
            throw new IllegalArgumentException("Invalid industry ID: " + id_industrie);
        }
        Auditeur auditeur = entityManager.find(Auditeur.class, id_auditeur);
        if (auditeur == null) {
            throw new IllegalArgumentException("Invalid auditor ID: " + id_auditeur);
        }
        audit.setIndustrie(industrie);
        audit.setAuditeur(auditeur);
        if (audit.getDuree() > 4 ) {
            throw new IllegalArgumentException("duree doit etre inferieure a 4 jours");
        }
        entityManager.persist(audit);
        return "Audit added successfully";
    }


//    @Override
//    @Transactional
//    public void update(int id_audit, Audit audit) {
//        Audit existingAudit = entityManager.find(Audit.class, id_audit);
//        if (existingAudit != null) {
//            existingAudit.setDate_debut(audit.getDate_debut());
//            existingAudit.setDuree(audit.getDuree());
//            existingAudit.setCout_jour(audit.getCout_jour());
//            existingAudit.setCout_total(audit.getCout_total());
//            existingAudit.setIndustrie(audit.getIndustrie());
//            existingAudit.setAuditeur(audit.getAuditeur());
//            entityManager.merge(existingAudit);
//        }
//    }


    @Override
    @Transactional
    public String update(Audit audit, int id_industrie, int id_auditeur) {
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
        return "audit updated successfully";
    }

    @Override
    public void delete(int id_audit) {
        auditRepository.deleteById(id_audit);
    }
}

