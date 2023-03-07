package fr.m2i.secureAudit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.m2i.secureAudit.model.Auditeur;


@Repository
public interface AuditeurRepository extends JpaRepository<Auditeur, Integer> {
}

