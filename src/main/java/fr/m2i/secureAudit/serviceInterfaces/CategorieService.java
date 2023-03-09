package fr.m2i.secureAudit.serviceInterfaces;

import fr.m2i.secureAudit.model.Categorie;

import java.util.List;

public interface CategorieService {

    Categorie getById(int id_categorie);

    List<Categorie> getCategories();

    void save(Categorie categorie);

    boolean update(int id_categorie, Categorie categorie);

    boolean deleteById(int id_categorie);
}

