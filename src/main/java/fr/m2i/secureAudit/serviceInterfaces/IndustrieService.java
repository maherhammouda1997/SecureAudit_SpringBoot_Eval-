package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Industrie;
import java.util.List;

// préparation des méthodes CRUD pour Industrie
public interface IndustrieService {

    Industrie findById(int id_industrie);

    List<Industrie> findAll();

    void save(Industrie industrie);

    void update(int id_industrie, Industrie industrie);

    boolean delete(int id_industrie);
}

