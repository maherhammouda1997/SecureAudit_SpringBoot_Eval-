package fr.m2i.secureAudit.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity // Annotation indiquant que cette classe est une entité JPA (Java Persistence API) qui peut être stockée en base de données
@Table(name = "frais") // Annotation permettant de spécifier le nom de la table correspondant à cette entité dans la base de données
public class Frais {
    @Id // Annotation indiquant que ce champ est une clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation permettant de générer automatiquement la valeur de la clé primaire
    private int id_frais; // Champ représentant l'identifiant du frais

    @Column(name = "date_debut_frais", nullable = false) // Annotation permettant de spécifier les attributs de la colonne correspondante dans la base de données
    private Date date_debut_frais; // Champ représentant la date de début du frais

    @Column(name = "montant", nullable = false) // Annotation permettant de spécifier les attributs de la colonne correspondante dans la base de données
    private int montant; // Champ représentant le montant du frais

    @Column(name = "est_rembourse", nullable = false) // Annotation permettant de spécifier les attributs de la colonne correspondante dans la base de données
    private Boolean est_rembourse; // Champ représentant si le frais a été remboursé ou non

    @ManyToOne // Annotation permettant de spécifier la relation entre deux entités
    @JoinColumn(name = "id_audit", nullable = false) // Annotation permettant de spécifier la clé étrangère correspondant à la relation
    private Audit audit; // Champ représentant l'audit associé au frais

    @ManyToOne // Annotation permettant de spécifier la relation entre deux entités
    @JoinColumn(name = "id_categorie", nullable = false) // Annotation permettant de spécifier la clé étrangère correspondant à la relation
    private Categorie categorie; // Champ représentant la catégorie du frais

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

