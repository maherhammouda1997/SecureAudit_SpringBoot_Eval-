package fr.m2i.secureAudit.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "frais")
public class Frais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_frais;

    @Column(name = "date_debut_frais", nullable = false)
    private Date date_debut_frais;

    @Column(name = "montant", nullable = false)
    private int montant;
    @Column(name = "est_rembourse", nullable = false)
    private Boolean est_rembourse;

    @ManyToOne
    @JoinColumn(name = "id_audit", nullable = false)
    private Audit audit;

    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categorie categorie;

    public Frais() {}

    public Frais(Date date_debut_frais, int montant, Boolean est_rembourse, Audit audit, Categorie categorie){
        this.date_debut_frais = date_debut_frais;
        this.montant = montant;
        this.est_rembourse = est_rembourse;
        this.audit = audit;
        this.categorie = categorie;
    }

    public int getId_frais() {
        return id_frais;
    }

    public void setId_frais(int id_frais) {
        this.id_frais = id_frais;
    }

    public Date getDate_debut_frais() {
        return date_debut_frais;
    }

    public void setDate_debut_frais(Date date_debut_frais) {
        this.date_debut_frais = date_debut_frais;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Boolean getEst_rembourse() {
        return est_rembourse;
    }

    public void setEst_rembourse(Boolean est_rembourse) {
        this.est_rembourse = est_rembourse;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

}

