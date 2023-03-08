package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Categorie;
import java.util.List;

public interface CategorieService {

    Categorie findById(int id_categorie);

    List<Categorie> findAll();

    void save(Categorie categorie);

    void update(int id_categorie, Categorie categorie);

    void delete(int id_categorie);
}

