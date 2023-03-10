package fr.m2i.secureAudit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "industrie") // Annotation pour spécifier que cette classe est mappée à une table appelée "industrie" dans la base de données
public class Industrie {
    @Id // Annotation pour spécifier que cet attribut est la clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation pour spécifier que la génération de la valeur de cette clé est gérée par la base de données
    private int id_industrie; // Attribut pour stocker l'identifiant unique de l'industrie

    @Column(name = "numero_siret", nullable = false)
    // Annotation pour spécifier que cet attribut est mappé à une colonne "numero_siret" dans la table, qui ne peut pas être null
    private Long numero_siret; // Attribut pour stocker le numéro SIRET de l'industrie

    @Column(name = "raison_sociale", nullable = false)
    // Annotation pour spécifier que cet attribut est mappé à une colonne "raison_sociale" dans la table, qui ne peut pas être null
    private String raison_sociale; // Attribut pour stocker la raison sociale de l'industrie

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