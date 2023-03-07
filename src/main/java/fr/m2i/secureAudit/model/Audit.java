package fr.m2i.secureAudit.model;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_audit;

    private Date date_debut;

    private int duree;

    private int cout_jour;

    private int cout_total;

    @ManyToOne
    @JoinColumn(name = "id_industrie")
    private Industrie industrie;

    @ManyToOne
    @JoinColumn(name = "id_auditeur")
    private Auditeur auditeur;

    public Audit() {}

    public Audit(Date date_debut, int duree, int cout_jour, int cout_total, Industrie industrie, Auditeur auditeur) {
        this.date_debut = date_debut ;
        this.duree = duree ;
        this.cout_jour = cout_jour ;
        this.cout_total = cout_total ;
        this.industrie = industrie ;
        this.auditeur = auditeur;
    }

    public int getIdAudit() {
        return id_audit;
    }

    public void setIdAudit(int id_audit) {
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



