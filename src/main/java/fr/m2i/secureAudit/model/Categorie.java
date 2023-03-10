package fr.m2i.secureAudit.model;

import jakarta.persistence.*;

@Entity // Cette annotation indique que la classe Categorie est une entité JPA.
@Table(name = "categorie") // Cette annotation indique que la table correspondant à cette entité s'appelle "categorie".
public class Categorie {
    @Id // Cette annotation indique que l'attribut "id_categorie" est la clé primaire de la table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cette annotation indique que la valeur de l'attribut "id_categorie" sera auto-générée par la base de données.
    private int id_categorie; // Cet attribut représente l'identifiant unique de la catégorie.

    @Column(name = "libelle", nullable = false)
    // Cette annotation indique que cet attribut est mappé à la colonne "libelle" de la table "categorie", et qu'il ne peut pas être nul.
    private String libelle; // Cet attribut représente le libellé de la catégorie.

    public Categorie() {}

    public Categorie (String libelle) {
        this.libelle = libelle;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

