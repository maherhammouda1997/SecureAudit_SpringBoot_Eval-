package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Categorie;
import fr.m2i.secureAudit.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Categorie findById(int id_categorie) {
        return categorieRepository.findById(id_categorie).orElse(null);
    }

    @Override
    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    @Override
    public void save(Categorie categorie) {
        categorieRepository.save(categorie);
    }

    @Override
    public void update(int id_categorie, Categorie categorie) {
        Categorie existingCategorie = categorieRepository.findById(id_categorie).orElse(null);
        if (existingCategorie != null) {
            existingCategorie.setLibelle(categorie.getLibelle());
            categorieRepository.save(existingCategorie);
        }
    }

    @Override
    public void delete(int id_categorie) {
        categorieRepository.deleteById(id_categorie);
    }
}

