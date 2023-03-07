package fr.m2i.secureAudit.model;

import jakarta.persistence.*;


@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_categorie;

    private String libelle;

    public Categorie() {}

    public Categorie (String libelle) {
        this.libelle = libelle;
    }

    public int getIdCategorie() {
        return id_categorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.id_categorie = idCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

