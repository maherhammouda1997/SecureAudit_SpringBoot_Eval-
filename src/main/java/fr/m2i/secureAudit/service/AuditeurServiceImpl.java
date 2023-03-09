package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Auditeur;
import fr.m2i.secureAudit.repository.AuditeurRepository;
import fr.m2i.secureAudit.serviceInterfaces.AuditeurService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditeurServiceImpl implements AuditeurService {

    @Autowired
    private AuditeurRepository auditeurRepository;

    private EntityManager entityManager;

    public AuditeurServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Auditeur> getAuditeurs() {
        return auditeurRepository.findAll();
    }

    @Override
    @Transactional
    public Auditeur rechercheById(int id_auditeur) {
        Query query = entityManager.createNativeQuery("SELECT * FROM auditeur a WHERE a.id_auditeur = :id_auditeur", Auditeur.class);
        query.setParameter("id_auditeur", id_auditeur);
        List<Auditeur> result = query.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public Auditeur createAuditeur(Auditeur auditeur) {
        return auditeurRepository.save(auditeur);
    }

    @Override
    @Transactional
    public boolean update(int id_auditeur, Auditeur auditeur) {
        Auditeur existingAuditeur = auditeurRepository.findById(id_auditeur).orElse(null);
        if (existingAuditeur != null) {
            existingAuditeur.setCivilite(auditeur.getCivilite());
            existingAuditeur.setNom(auditeur.getNom());
            existingAuditeur.setPrenom(auditeur.getPrenom());
            auditeurRepository.save(existingAuditeur);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(int id_auditeur) {
        if (auditeurRepository.existsById(id_auditeur)) {
            auditeurRepository.deleteById(id_auditeur);
            return true;
        }
        return false;
    }
}

