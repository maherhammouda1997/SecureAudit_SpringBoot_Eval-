package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Industrie;
import java.util.List;

public interface IndustrieService {

    Industrie findById(int id_industrie);

    List<Industrie> findAll();

    void save(Industrie industrie);

    void update(int id_industrie, Industrie industrie);

    void delete(int id_industrie);
}

