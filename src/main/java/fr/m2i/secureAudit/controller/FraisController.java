package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Audit;
import fr.m2i.secureAudit.model.Auditeur;
import fr.m2i.secureAudit.model.Categorie;
import fr.m2i.secureAudit.model.Frais;
import fr.m2i.secureAudit.serviceInterfaces.AuditService;
import fr.m2i.secureAudit.serviceInterfaces.AuditeurService;
import fr.m2i.secureAudit.serviceInterfaces.CategorieService;
import fr.m2i.secureAudit.serviceInterfaces.FraisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frais")
public class FraisController {

    // Injection de dépendances
    @Autowired
    private FraisService fraisService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private AuditeurService auditeurService;

    // Endpoint pour récupérer tous les frais
    @GetMapping("/get")
    public ResponseEntity<List<Frais>> getAllFrais() {
        List<Frais> fraisList = fraisService.findAll();
        return ResponseEntity.ok().body(fraisList);
    }

    // Endpoint pour récupérer un frais par son id
    @GetMapping("/get/{id_frais}")
    public ResponseEntity<?> getFraisById(@PathVariable int id_frais) {
        Frais frais = fraisService.findById(id_frais);
        if (frais != null) {
            return ResponseEntity.ok(frais);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("frais not found !");
        }
    }

    // Endpoint pour ajouter un frais
    @PostMapping("/post/{id_audit}/{id_categorie}")
    public ResponseEntity<String> addFrais(@PathVariable int id_audit, @PathVariable int id_categorie ,@RequestBody Frais frais) {
        try {
            // Vérification de l'existence de l'audit
            Audit audit = auditService.findById(id_audit);
            if (audit == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Audit not found");
            }
            // Vérification de l'existence de la catégorie
            Categorie categorie = categorieService.getById(id_categorie);
            if (categorie == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categorie not found");
            }
            // Ajout du frais
            fraisService.addFrais(frais, id_audit, id_categorie);
            return ResponseEntity.status(HttpStatus.CREATED).body("frais added successfully");
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint pour mettre à jour un frais
    @PutMapping("/put/{id_frais}")
    public ResponseEntity<String> updateFrais(@PathVariable int id_frais, @RequestBody Frais frais) {
        try {
            frais.setId_frais(id_frais);
            int id_audit = frais.getAudit() != null ? frais.getAudit().getId_audit() : 0;
            int id_categorie = frais.getCategorie() != null ? frais.getCategorie().getId_categorie() : 0;

            // Vérification de l'existence du frais
            Frais existingFrais = fraisService.findById(id_frais);
            if (existingFrais == null) {
                return new ResponseEntity<>("Frais not found", HttpStatus.NOT_FOUND);
            }

            // Vérification de l'existence de l'audit
            Audit audit = auditService.findById(id_audit);
            if (audit == null) {
                return new ResponseEntity<>("Audit not found", HttpStatus.BAD_REQUEST);
            }

            // Vérification de l'existence de la catégorie
            Categorie categorie = categorieService.getById(id_categorie);
            if (categorie == null) {
                return new ResponseEntity<>("Categorie not found", HttpStatus.BAD_REQUEST);
            }
            frais.setAudit(audit);
            frais.setCategorie(categorie);
            // Mise à jour du frais
            fraisService.update(id_frais, frais);
            return ResponseEntity.ok("Frais updated successfully");
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint pour supprimer un frais
    @DeleteMapping("/del/{id_frais}")
    public ResponseEntity<String> deleteFrais(@PathVariable int id_frais) {
        boolean isDeleted = fraisService.delete(id_frais);
        if (isDeleted) {
            String message = "Frais deleted successfully";
            return ResponseEntity.ok(message);
        } else {
    // renvoie un message d'erreur si le frais n'est pas trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Frais not found");
        }
    }

    // Endpoint pour récupérer les frais d'un auditeur
    @GetMapping("/get/auditeur/{id_auditeur}")
    public ResponseEntity<?> findFraisByAuditeur(@PathVariable int id_auditeur) {
        // recherche l'auditeur correspondant à l'id_auditeur donné
        Auditeur auditeur = auditeurService.rechercheById(id_auditeur);
        if (auditeur == null) {
            // renvoie un message d'erreur si l'auditeur n'est pas trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Auditeur not found !");
        }
        // recherche les frais de l'auditeur
        List<Frais> fraisList = fraisService.findFraisByAuditeur(id_auditeur);
        if (fraisList.isEmpty()) {
            // renvoie un message d'erreur si aucun frais n'a été trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No frais for this Auditeur!");
        } else {
            // renvoie la liste des frais de l'auditeur
            return ResponseEntity.ok(fraisList);
        }
    }

    // Endpoint pour récupérer les frais d'un audit
    @GetMapping("/get/audit/{id_audit}")
    public ResponseEntity<?> findFraisByAudit(@PathVariable int id_audit) {
        // recherche l'audit correspondant à l'id_audit donné
        Audit audit = auditService.findById(id_audit);
        if (audit == null) {
            // renvoie un message d'erreur si l'audit n'est pas trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Audit not found !");
        }
        // recherche les frais de l'audit
        List<Frais> fraisList = fraisService.findFraisByAudit(id_audit);
        if (fraisList.isEmpty()) {
            // renvoie un message d'erreur si aucun frais n'a été trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No frais for this Audit!");
        } else {
            // renvoie la liste des frais de l'audit
            return ResponseEntity.ok(fraisList);
        }
    }

    // Endpoint pour récupérer les frais d'une catégorie
    @GetMapping("/get/categorie/{id_categorie}")
    public ResponseEntity<?> findFraisByCategorie(@PathVariable int id_categorie) {
        // recherche les frais de la catégorie
        List<Frais> fraisList = fraisService.findFraisByCategorie(id_categorie);
        if (fraisList.isEmpty()) {
            // renvoie un message d'erreur si aucun frais n'a été trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No frais for this Categorie!");
        } else {
            // renvoie la liste des frais de la catégorie
            return ResponseEntity.ok(fraisList);
        }
    }
}

