package fr.m2i.secureAudit.repository;

import fr.m2i.secureAudit.model.Frais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Annotation de Spring indiquant que cette interface est un repository
@Repository
public interface FraisRepository extends JpaRepository<Frais, Integer> {
}
