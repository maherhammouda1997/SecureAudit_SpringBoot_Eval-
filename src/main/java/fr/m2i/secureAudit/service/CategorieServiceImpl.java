package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Categorie;
import fr.m2i.secureAudit.repository.CategorieRepository;
import fr.m2i.secureAudit.serviceInterfaces.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    // Retourne toutes les catégories dans la base de données
    @Override
    public List<Categorie> getCategories() {
        return categorieRepository.findAll();
    }

    // Retourne la catégorie correspondant à l'id donné en entrée
    @Override
    public Categorie getById(int id_categorie) {
        return categorieRepository.findById(id_categorie).orElse(null);
    }

    // Sauvegarde la catégorie donnée en entrée dans la base de données
    // Vérifie si le champ "libelle" de la catégorie est renseigné
    @Override
    public void save(Categorie categorie) {
        if (categorie.getLibelle() == null) {
            throw new IllegalArgumentException("All fields are required.");
        }
        categorieRepository.save(categorie);
    }

    // Met à jour la catégorie correspondant à l'id donné en entrée avec les données de la catégorie donnée en entrée
    // Vérifie si le champ "libelle" de la catégorie est renseigné
    @Override
    public boolean update(int id_categorie, Categorie categorie) {
        if (categorie.getLibelle() == null) {
            throw new IllegalArgumentException("All fields are required.");
        }
        Categorie existingCategorie = categorieRepository.findById(id_categorie).orElse(null);
        if (existingCategorie != null) {
            existingCategorie.setLibelle(categorie.getLibelle());
            categorieRepository.save(existingCategorie);
            return true;
        }
        return false;
    }

    // Supprime la catégorie correspondant à l'id donné en entrée de la base de données
    @Override
    public boolean deleteById(int id_categorie) {
        if (categorieRepository.existsById(id_categorie)) {
            categorieRepository.deleteById(id_categorie);
            return true;
        }
        return false;
    }

}

