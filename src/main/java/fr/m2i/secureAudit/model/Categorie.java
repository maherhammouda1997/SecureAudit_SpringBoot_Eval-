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

//    public int getIdCategorie() {
//        return id_categorie;
//    }
//
//    public void setIdCategorie(int id_categorie) {
//        this.id_categorie = id_categorie;
//    }
//
//    public String getLibelle() {
//        return libelle;
//    }
//
//    public void setLibelle(String libelle) {
//        this.libelle = libelle;
//    }


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

