package fr.m2i.secureAudit.serviceInterfaces;

import java.util.List;
import fr.m2i.secureAudit.model.Frais;

public interface FraisService {

    Frais findById(int id_frais);

    List<Frais> findAll();

    String addFrais(Frais frais, int id_audit, int id_categorie);

    String update(int id_frais, Frais frais);

    boolean delete(int id_frais);

    List<Frais> findFraisByCategorie(int id_categorie);

    List<Frais> findFraisByAuditeur(int id_auditeur);

    List<Frais> findFraisByAudit(int id_audit);

}

