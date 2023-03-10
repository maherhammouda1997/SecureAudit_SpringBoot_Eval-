package fr.m2i.secureAudit.model;

import jakarta.persistence.*;

// Déclaration de la classe Auditeur comme entité persistante
@Entity
// Spécification du nom de la table associée à l'entité Auditeur
@Table(name = "auditeur")
public class Auditeur {
    // Déclaration de l'attribut id_auditeur comme clé primaire
    @Id
    // Spécification de la stratégie de génération de la valeur de l'attribut id_auditeur
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_auditeur;

    // Déclaration de l'attribut civilite comme une colonne de la table auditeur
    // Spécification de l'obligation de remplir cette colonne (nullable = false)
    // Spécification de la longueur maximale de la valeur de la colonne (length = 3)
    @Column(name = "civilite", nullable = false, length = 3)
    private String civilite;

    // Déclaration de l'attribut nom comme une colonne de la table auditeur
    // Spécification de l'obligation de remplir cette colonne (nullable = false)
    @Column(name = "nom", nullable = false)
    private String nom;

    // Déclaration de l'attribut prenom comme une colonne de la table auditeur
    // Spécification de l'obligation de remplir cette colonne (nullable = false)
    @Column(name = "prenom", nullable = false)
    private String prenom;

    // Constructeur par défaut de la classe Auditeur
    public Auditeur() {};

    // Constructeur de la classe Auditeur avec les paramètres civilite, nom et prenom
    public Auditeur(String civilite, String nom, String prenom) {
        this.civilite = civilite;
        this.nom = nom ;
        this.prenom = prenom;
    }

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
