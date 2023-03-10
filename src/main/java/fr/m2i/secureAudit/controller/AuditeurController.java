package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Auditeur;
import fr.m2i.secureAudit.serviceInterfaces.AuditeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //cette annotation indique que la classe est un contrôleur qui gère les requêtes web et renvoie des réponses HTTP.
@RequestMapping("/auditeur") //cette annotation indique que toutes les requêtes pour cette classe seront gérées par la route "/auditeur".
@CrossOrigin //cette annotation permet d'autoriser les requêtes HTTP provenant de sources autres que le serveur sur lequel est déployée l'application
public class AuditeurController {

    @Autowired //cette annotation permet d'injecter automatiquement une instance de la classe AuditeurService dans le contrôleur
    private AuditeurService auditeurService;

    @GetMapping //cette annotation indique qu'il s'agit d'une méthode qui répond aux requêtes HTTP GET.
    public String getMessage() {
        return "Welcome to our Spring Boot project!";
    }

    @GetMapping("/get") //cette annotation indique qu'il s'agit d'une méthode qui répond aux requêtes HTTP GET.
    public ResponseEntity<List<Auditeur>> getAuditeurs() {
        List<Auditeur> auditeurs = auditeurService.getAuditeurs();
        return new ResponseEntity<List<Auditeur>>(auditeurs, HttpStatus.OK);
       // ResponseEntity : cette classe permet de retourner une réponse HTTP personnalisée avec un corps et un code de statut
    }

    @GetMapping("/get/{id_auditeur}")
    public ResponseEntity<?> getAuditeurById(@PathVariable("id_auditeur") int id_auditeur) {
        // PathVariable : cette annotation permet de récupérer la valeur de la variable "id_auditeur" de l'URL
        Auditeur auditeur = auditeurService.rechercheById(id_auditeur);
        if (auditeur == null) {
            String message = "Auditeur with ID " + id_auditeur + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok().body(auditeur);
        }
    }

    @PostMapping("/post") //cette annotation indique qu'il s'agit d'une méthode qui répond aux requêtes HTTP POST
    public ResponseEntity<String> createAuditeur(@RequestBody Auditeur auditeur) {
        //RequestBody : cette annotation permet de désérialiser le corps de la requête en un objet Java
        try {
            Auditeur createdAuditeur = auditeurService.createAuditeur(auditeur);
            if (createdAuditeur == null) {
                return new ResponseEntity<>("Error, bad request ! :( ", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>("new Auditeur added", HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put/{id_auditeur}") //cette annotation indique qu'il s'agit d'une méthode qui répond aux requêtes HTTP PUT
    public ResponseEntity<String> updateAuditeur(@PathVariable("id_auditeur") int id_auditeur, @RequestBody Auditeur auditeur) {
        try {
            boolean updatedAuditeur = auditeurService.update(id_auditeur, auditeur);
            if (!updatedAuditeur) {
                String message = "Auditeur with ID " + id_auditeur + " not found.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            } else {
                String message = "Auditeur updated successfully";
                return ResponseEntity.status(HttpStatus.OK).body(message);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/del/{id_auditeur}") //cette annotation indique qu'il s'agit d'une méthode qui répond aux requêtes HTTP DELETE
    public ResponseEntity<String> deleteAuditeurById(@PathVariable("id_auditeur") int id_auditeur) {
        boolean deleted = auditeurService.deleteById(id_auditeur);
        if (!deleted) {
            String message = "Auditeur not found!";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            String message = "Auditeur deleted successfully";
            return ResponseEntity.ok(message);
        }
    }
}

