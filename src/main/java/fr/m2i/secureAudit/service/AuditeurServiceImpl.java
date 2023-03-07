package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Auditeur;
import fr.m2i.secureAudit.repository.AuditeurRepository;
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
    public Auditeur findById(int id_auditeur) {
        Query query = entityManager.createNativeQuery("SELECT * FROM Auditeur a WHERE a.idAuditeur = :idAuditeur");
        query.setParameter("idAuditeur", id_auditeur);
        return (Auditeur) query.getSingleResult();
    }


    @Override
    public Auditeur save(Auditeur auditeur) {
        return auditeurRepository.save(auditeur);
    }

    @Override
    public void update(int id_auditeur, Auditeur auditeur) {
        Auditeur existingAuditeur = entityManager.find(Auditeur.class, id_auditeur);
        if (existingAuditeur != null) {
            existingAuditeur.setCivilite(auditeur.getCivilite());
            existingAuditeur.setNom(auditeur.getNom());
            existingAuditeur.setPrenom(auditeur.getPrenom());
            entityManager.merge(existingAuditeur);
        }
    }


    @Override
    public void deleteById(int id_auditeur) {
        auditeurRepository.deleteById(id_auditeur);
    }
}

