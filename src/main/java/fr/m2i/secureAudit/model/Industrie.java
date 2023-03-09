package fr.m2i.secureAudit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "industrie")
public class Industrie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_industrie;

    @Column(name = "numero_siret", nullable = false)
    private Long numero_siret;

    @Column(name = "raison_sociale", nullable = false)
    private String raison_sociale;

    public Industrie() {}

    public Industrie(long numero_Siret, String raison_sociale) {
        this.numero_siret = numero_siret;
        this.raison_sociale = raison_sociale;
    }

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

}