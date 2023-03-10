package fr.m2i.secureAudit.repository;

import fr.m2i.secureAudit.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Annotation de Spring indiquant que cette interface est un repository
@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {
    // Cette interface ne contient pas de méthode car JpaRepository fournit des méthodes CRUD de base
}
