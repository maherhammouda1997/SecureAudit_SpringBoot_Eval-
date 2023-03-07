package fr.m2i.secureAudit.repository;

import fr.m2i.secureAudit.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {
}
