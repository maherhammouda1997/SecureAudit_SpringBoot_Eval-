package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Categorie;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CategorieService {

    Categorie getById(int id_categorie);

    List<Categorie> getCategories();

    void save(Categorie categorie);

    @Transactional
    void update(int id_categorie, Categorie categorie);

    void deleteById(int id_categorie);
}

