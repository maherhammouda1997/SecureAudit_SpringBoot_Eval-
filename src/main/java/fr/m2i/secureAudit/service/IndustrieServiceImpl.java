package fr.m2i.secureAudit.service;

import fr.m2i.secureAudit.model.Industrie;
import fr.m2i.secureAudit.repository.IndustrieRepository;
import fr.m2i.secureAudit.serviceInterfaces.IndustrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndustrieServiceImpl implements IndustrieService {

    private final IndustrieRepository industrieRepository;

    @Autowired
    public IndustrieServiceImpl(IndustrieRepository industrieRepository) {
        this.industrieRepository = industrieRepository;
    }

    /**
     * Recherche d'une industrie par son identifiant
     *
     * @param id_industrie identifiant de l'industrie recherchée
     * @return l'industrie trouvée, ou null si aucune industrie ne correspond à l'identifiant fourni
     */
    @Override
    public Industrie findById(int id_industrie) {
        return industrieRepository.findById(id_industrie).orElse(null);
    }

    /**
     * Récupère la liste de toutes les industries enregistrées dans la base de données
     *
     * @return la liste de toutes les industries enregistrées dans la base de données
     */
    @Override
    public List<Industrie> findAll() {
        return industrieRepository.findAll();
    }

    /**
     * Enregistre une nouvelle industrie dans la base de données
     *
     * @param industrie l'industrie à enregistrer
     * @throws IllegalArgumentException si un ou plusieurs champs obligatoires sont manquants, ou si le numéro SIRET est invalide
     */
    public void save(Industrie industrie) {
        if (industrie.getRaison_sociale() == null) {
            throw new IllegalArgumentException("All fields are required.");
        }
        Long siret = industrie.getNumero_siret();
        String siretString = String.valueOf(siret);
        if (siretString.length() == 14) {
            industrieRepository.save(industrie);
        } else {
            throw new IllegalArgumentException("Check the SIRET number : " + siretString);
        }
    }

    /**
     * Met à jour une industrie existante dans la base de données
     *
     * @param id_industrie identifiant de l'industrie à mettre à jour
     * @param industrie les nouvelles données de l'industrie
     * @throws IllegalArgumentException si un ou plusieurs champs obligatoires sont manquants, ou si le numéro SIRET est invalide
     */
    @Override
    public void update(int id_industrie, Industrie industrie) {
        if (industrie.getRaison_sociale() == null) {
            throw new IllegalArgumentException("All fields are required.");
        }
        Industrie existingIndustrie = industrieRepository.findById(id_industrie).orElse(null);
        if (existingIndustrie != null) {
            Long siret = industrie.getNumero_siret();
            String siretString = String.valueOf(siret);
            if (siretString.length() == 14) {
                existingIndustrie.setNumero_siret(siret);
                existingIndustrie.setRaison_sociale(industrie.getRaison_sociale());
                industrieRepository.save(existingIndustrie);
            } else {
                throw new IllegalArgumentException("Check the SIRET number : " + siretString);
            }
        }
    }

    /**
     * Supprime une industrie de la base de données
     *
     * @param id_industrie identifiant de l'industrie à supprimer
     * @return true si l'industrie a été supprimée avec succès, false sinon
     */
    @Override
    public boolean delete(int id_industrie) {
        if (industrieRepository.existsById(id_industrie)) {
            industrieRepository.deleteById(id_industrie);
            return true;
        }
        return false;
    }
}

