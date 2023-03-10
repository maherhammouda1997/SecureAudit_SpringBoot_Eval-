package fr.m2i.secureAudit.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity // objet Audit ou en va stocker ou récupérer des données
@Table(name = "audit") // nom de la table correspondante dans la base de données
public class Audit {
    @Id // la propriété id_audit est l'identifiant de l'objet
    @GeneratedValue(strategy = GenerationType.IDENTITY) // l'autoIncrémentation est assurée par la base de données
    private int id_audit;

    @Column(name = "date_debut", nullable = false) // nom de la colonne correspondante dans la base de données et indication qu'elle ne peut pas être nulle
    private Date date_debut; // date de début de l'audit

    @Column(name = "duree", nullable = false) // nom de la colonne correspondante dans la base de données
    private int duree; // durée de l'audit en jours

    @Column(name = "cout_jour", nullable = false) // nom de la colonne correspondante dans la base de données
    private int cout_jour; // coût d'une journée d'audit

    @Column(name = "cout_total", nullable = false) // nom de la colonne correspondante dans la base de données
    private int cout_total; // coût total de l'audit

    @ManyToOne // relation many-to-one entre Audit et Industrie (plusieurs audits peuvent être réalisés dans une même industrie)
    @JoinColumn(name = "id_industrie", nullable = false) // nom de la colonne correspondante dans la base de données
    private Industrie industrie; // l'industrie dans laquelle l'audit est réalisé

    @ManyToOne // relation many-to-one entre Audit et Auditeur (plusieurs audits peuvent être réalisés par le même auditeur)
    @JoinColumn(name = "id_auditeur", nullable = false) // nom de la colonne correspondante dans la base de données
    private Auditeur auditeur; // l'auditeur qui réalise l'audit

    public Audit() {} //constructeur vide // bonne pratique pour répondre aux exigences de JPA

    public Audit(Date date_debut, int duree, int cout_jour, int cout_total, Industrie industrie, Auditeur auditeur) {
        this.date_debut = date_debut ;
        this.duree = duree ;
        this.cout_jour = cout_jour ;
        this.cout_total = cout_total ;
        this.industrie = industrie ;
        this.auditeur = auditeur;
    }

    public int getId_audit() {
        return id_audit;
    }

    public void setId_audit(int id_audit) {
        this.id_audit = id_audit;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getCout_jour() {
        return cout_jour;
    }

    public void setCout_jour(int cout_jour) {
        this.cout_jour = cout_jour;
    }

    public int getCout_total() {
        return cout_total;
    }

    public void setCout_total(int cout_total) {
        this.cout_total = cout_total;
    }

    public Industrie getIndustrie() {
        return industrie;
    }

    public void setIndustrie(Industrie industrie) {
        this.industrie = industrie;
    }

    public Auditeur getAuditeur() {
        return auditeur;
    }

    public void setAuditeur(Auditeur auditeur) {
        this.auditeur = auditeur;
    }
}



