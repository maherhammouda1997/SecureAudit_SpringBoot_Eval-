package fr.m2i.secureAudit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "auditeur")
public class Auditeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_auditeur;

    private String civilite;

    private String nom;

    private String prenom;

    public Auditeur() {};
    public Auditeur(String civilite, String nom, String prenom) {
        this.civilite = civilite;
        this.nom = nom ;
        this.prenom = prenom;
    }

//    public int getIdAuditeur() {
//        return id_auditeur;
//    }
//
//    public void setIdAuditeur(int id_auditeur) {
//        this.id_auditeur = id_auditeur;
//    }
//
//    public String getCivilite() {
//        return civilite;
//    }
//
//    public void setCivilite(String civilite) {
//        this.civilite = civilite;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getPrenom() {
//        return prenom;
//    }
//
//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }


    public int getId_auditeur() {
        return id_auditeur;
    }

    public void setId_auditeur(int id_auditeur) {
        this.id_auditeur = id_auditeur;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
