package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Categorie;
import fr.m2i.secureAudit.repository.CategorieRepository;
import fr.m2i.secureAudit.serviceInterfaces.CategorieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public List<Categorie> getCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getById(int id_categorie) {
        return categorieRepository.findById(id_categorie).orElse(null);
    }

    @Override
    public void save(Categorie categorie) {
        categorieRepository.save(categorie);
    }

    @Override
    @Transactional
    public void update(int id_categorie, Categorie categorie) {
        Categorie existingCategorie = categorieRepository.findById(id_categorie).orElse(null);
        if (existingCategorie != null) {
            existingCategorie.setLibelle(categorie.getLibelle());
            categorieRepository.save(existingCategorie);
        }
    }

    @Override
    public void deleteById(int id_categorie) {
        categorieRepository.deleteById(id_categorie);
    }
}

