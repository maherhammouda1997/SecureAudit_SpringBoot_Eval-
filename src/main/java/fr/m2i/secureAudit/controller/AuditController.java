package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Audit;
import fr.m2i.secureAudit.model.Auditeur;
import fr.m2i.secureAudit.model.Industrie;
import fr.m2i.secureAudit.serviceInterfaces.AuditService;
import fr.m2i.secureAudit.serviceInterfaces.AuditeurService;
import fr.m2i.secureAudit.serviceInterfaces.IndustrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController // Décorateur indiquant que c'est un contrôleur REST
@RequestMapping("/audit") // Indique la racine des URL gérées par ce contrôleur

public class AuditController {

    // Injection de dépendances des services nécessaires à la classe
    @Autowired
    private AuditService auditService;
    @Autowired
    private IndustrieService industrieService;
    @Autowired
    private AuditeurService auditeurService;

    // Endpoint pour récupérer tous les audits
    @GetMapping("/get")
    public ResponseEntity<List<Audit>> getAudit() {
        List<Audit> audits = auditService.getAudit();
        return new ResponseEntity<List<Audit>>(audits, HttpStatus.OK);
    }

    // Endpoint pour récupérer un audit par son ID
    @GetMapping("/get/{id_audit}")
    public ResponseEntity<?> getAuditById(@PathVariable int id_audit) {
        // PathVariable : cette annotation permet de récupérer la valeur de la variable "id_audit" de l'URL
        Audit audit = auditService.findById(id_audit);
        if (audit == null) {
            String message = "Audit with ID " + id_audit + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            //ResponseEntity : cette classe permet de retourner une réponse HTTP personnalisée avec un corps et un code de statut
        }
        return ResponseEntity.ok(audit);
    }

    // Endpoint pour ajouter un nouvel audit
    @PostMapping("/post/{id_industrie}/{id_auditeur}")
    public ResponseEntity<String> addAudit(@PathVariable int id_industrie, @PathVariable int id_auditeur ,@RequestBody Audit audit) {
        //RequestBody : cette annotation permet de désérialiser le corps de la requête en un objet Java
        try {
            // Vérifie si l'industrie existe
            Industrie industrie = industrieService.findById(id_industrie);
            if (industrie == null) {
                return new ResponseEntity<>("Industrie not found", HttpStatus.NOT_FOUND);
            }
            // Vérifie si l'auditeur existe
            Auditeur auditeur = auditeurService.rechercheById(id_auditeur);
            if (auditeur == null) {
                return new ResponseEntity<>("Auditeur not found", HttpStatus.NOT_FOUND);
            }
            // Ajoute l'audit en base de données
            Audit addedAudit = auditService.addAudit(audit, id_industrie, id_auditeur);
            return new ResponseEntity<>("Audit added successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Retourne une erreur 400 si l'audit n'a pas pu être ajouté
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Mise à jour d'un audit existant
    @PutMapping("/put/{id_audit}")
    public ResponseEntity<String> updateAudit(@PathVariable int id_audit, @RequestBody Audit audit) {
        try {
            // Affectation de l'ID de l'audit à l'objet audit
            audit.setId_audit(id_audit);
            // Récupération de l'ID de l'industrie associée à l'audit s'il existe, sinon 0
            int id_industrie = audit.getIndustrie() != null ? audit.getIndustrie().getId_industrie() : 0;
            // Récupération de l'ID de l'auditeur associé à l'audit s'il existe, sinon 0
            int id_auditeur = audit.getAuditeur() != null ? audit.getAuditeur().getId_auditeur() : 0;
            // Vérification si l'audit existant est présent dans la base de données
            Audit existingAudit = auditService.findById(id_audit);
            if (existingAudit == null) {
                // Renvoie une réponse HTTP NOT_FOUND si l'audit n'est pas trouvé
                return new ResponseEntity<>("Audit not found", HttpStatus.NOT_FOUND);
            }
            // Récupération de l'industrie à partir de l'ID
            Industrie industrie = industrieService.findById(id_industrie);
            if (industrie == null) {
                // Renvoie une réponse HTTP NOT_FOUND si l'industrie n'est pas trouvée
                return new ResponseEntity<>("Industrie not found", HttpStatus.NOT_FOUND);
            }
            // Récupération de l'auditeur à partir de l'ID
            Auditeur auditeur = auditeurService.rechercheById(id_auditeur);
            if (auditeur == null) {
                // Renvoie une réponse HTTP NOT_FOUND si l'auditeur n'est pas trouvé
                return new ResponseEntity<>("Auditeur not found", HttpStatus.NOT_FOUND);
            }
            // Affectation de l'industrie et de l'auditeur à l'objet audit
            audit.setIndustrie(industrie);
            audit.setAuditeur(auditeur);
            // Appel de la méthode de service pour mettre à jour l'audit
            String result = auditService.update(audit, id_industrie, id_auditeur);
            // Renvoie une réponse HTTP OK avec le message de succès de la mise à jour
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            // Renvoie une réponse HTTP BAD_REQUEST si les arguments sont invalides
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Suppression d'un audit existant
    @DeleteMapping("/del/{id_audit}")
    public ResponseEntity<String> deleteAudit(@PathVariable int id_audit) {
        // Appel de la méthode de service pour supprimer l'audit
        boolean isDeleted = auditService.delete(id_audit);
        if (isDeleted) {
            // Renvoie une réponse HTTP OK avec le message de succès de la suppression
            String message = "Audit deleted successfully";
            return ResponseEntity.ok(message);
        } else {
            // Renvoie une réponse HTTP NOT_FOUND si l'audit n'est pas trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Audit not found");
        }
    }

    @GetMapping("/get/industrie/{id_industrie}") // Endpoint pour récupérer tous les audits associés à une industrie donnée
    public ResponseEntity<?> getAuditsByIndustrie(@PathVariable int id_industrie) {
        List<Audit> audits = auditService.findByIndustrie(id_industrie); // Appel à la méthode de service pour récupérer les audits correspondants à l'id de l'industrie fourni
        if (audits.isEmpty()) { // Si la liste d'audits est vide
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Industrie not found."); // Retourner une réponse HTTP avec un code 404 Not Found et un message d'erreur
        }
        return ResponseEntity.ok(audits); // Retourner une réponse HTTP avec un code 200 OK et la liste d'audits correspondants à l'industrie fournie
    }

    // Afficher les audits par id_auditeur
    @GetMapping("/get/auditeur/{id_auditeur}") // Endpoint pour récupérer tous les audits associés à un auditeur donné
    public ResponseEntity<?> getAuditsByAuditeur(@PathVariable int id_auditeur) {
        List<Audit> audits = auditService.findByAuditeur(id_auditeur); // Appel à la méthode de service pour récupérer les audits correspondants à l'id de l'auditeur fourni
        if (audits.isEmpty()) { // Si la liste d'audits est vide
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Auditeur not found."); // Retourner une réponse HTTP avec un code 404 Not Found et un message d'erreur
        }
        return ResponseEntity.ok(audits); // Retourner une réponse HTTP avec un code 200 OK et la liste d'audits correspondants à l'auditeur fourni
    }

}

