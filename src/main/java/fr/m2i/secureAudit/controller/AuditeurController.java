package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Auditeur;
import fr.m2i.secureAudit.serviceInterfaces.AuditeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/auditeur")
@CrossOrigin
public class AuditeurController {

    @Autowired
    private AuditeurService auditeurService;

    @GetMapping
    public String getMessage() {
        return "Welcome to our Spring Boot project!";
    }

    @GetMapping("/get")
    public ResponseEntity<List<Auditeur>> getAuditeurs() {
        List<Auditeur> auditeurs = auditeurService.getAuditeurs();
        return new ResponseEntity<List<Auditeur>>(auditeurs, HttpStatus.OK);
    }

    @GetMapping("/get/{id_auditeur}")
    public ResponseEntity<?> getAuditeurById(@PathVariable("id_auditeur") int id_auditeur) {
        Auditeur auditeur = auditeurService.rechercheById(id_auditeur);
        if (auditeur == null) {
            String message = "Auditeur with ID " + id_auditeur + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok().body(auditeur);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<String> createAuditeur(@RequestBody Auditeur auditeur) {
        Auditeur createdAuditeur = auditeurService.createAuditeur(auditeur);
        if (createdAuditeur == null) {
            return new ResponseEntity<>("Error, bad request ! :( ", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("new Auditeur added", HttpStatus.CREATED);
        }
    }

    @PutMapping("/put/{id_auditeur}")
    public ResponseEntity<String> updateAuditeur(@PathVariable("id_auditeur") int id_auditeur, @RequestBody Auditeur auditeur) {
        boolean updatedAuditeur = auditeurService.update(id_auditeur, auditeur);
        if (!updatedAuditeur ) {
            String message = "Auditeur with ID " + id_auditeur + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            String message = "Auditeur updated successfully";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    @DeleteMapping("/del/{id_auditeur}")
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

