package fr.m2i.secureAudit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "industrie")
public class Industrie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_industrie;

    private Long numero_siret;

    private String raison_sociale;

    @ManyToOne
    @JoinColumn(name = "id_auditeur")
    private Auditeur auditeur;

    public Industrie() {}

    public Industrie(int numero_Siret, String raison_sociale, Auditeur auditeur) {
        this.numero_siret = numero_siret;
        this.raison_sociale = raison_sociale;
        this.auditeur = auditeur;
    }

//    public int getIdIndustrie() {
//        return id_industrie;
//    }
//
//    public void setIdIndustrie(int id_industrie) {
//        this.id_industrie = id_industrie;
//    }
//
//    public Long getNumeroSiret() {
//        return numero_siret;
//    }
//
//    public void setNumeroSiret(Long numero_siret) {
//        this.numero_siret = numero_siret;
//    }
//
//    public String getRaisonSociale() {
//        return raison_sociale;
//    }
//
//    public void setRaisonSociale(String raison_sociale) {
//        this.raison_sociale = raison_sociale;
//    }
//
//    public Auditeur getAuditeur() {
//        return auditeur;
//    }
//
//    public void setAuditeur(Auditeur auditeur) {
//        this.auditeur = auditeur;
//    }


    public int getId_industrie() {
        return id_industrie;
    }

    public void setId_industrie(int id_industrie) {
        this.id_industrie = id_industrie;
    }

    public Long getNumero_siret() {
        return numero_siret;
    }

    public void setNumero_siret(Long numero_siret) {
        this.numero_siret = numero_siret;
    }

    public String getRaison_sociale() {
        return raison_sociale;
    }

    public void setRaison_sociale(String raison_sociale) {
        this.raison_sociale = raison_sociale;
    }

    public Auditeur getAuditeur() {
        return auditeur;
    }

    public void setAuditeur(Auditeur auditeur) {
        this.auditeur = auditeur;
    }
}

