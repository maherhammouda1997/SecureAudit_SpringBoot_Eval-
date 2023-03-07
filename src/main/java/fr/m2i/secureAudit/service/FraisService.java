package fr.m2i.secureAudit.service;

import java.util.List;
import fr.m2i.secureAudit.model.Frais;

public interface FraisService {

    Frais findById(int id_frais);

    List<Frais> findAll();

    void save(Frais frais);

    void update(int id_frais, Frais frais);

    void delete(int id_frais);
}

