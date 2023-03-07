package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Auditeur;
import fr.m2i.secureAudit.service.AuditeurService;
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
    public List<Auditeur> getAuditeurs() {
            return auditeurService.getAuditeurs();
    }

    @GetMapping("/get/{id_auditeur}")
    public ResponseEntity<Auditeur> getAuditeurById(@PathVariable("id_auditeur") int id_auditeur) {
        Auditeur auditeur = auditeurService.findById(id_auditeur);
        return ResponseEntity.ok().body(auditeur);
    }

    @PostMapping("/post")
    public ResponseEntity<Auditeur> createAuditeur(@RequestBody Auditeur auditeur) {
        Auditeur createdAuditeur = auditeurService.save(auditeur);
        return new ResponseEntity<>(createdAuditeur, HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Void> updateAuditeur(@PathVariable("id_auditeur") int id_auditeur, @RequestBody Auditeur auditeur) {
        auditeurService.update(id_auditeur, auditeur);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteAuditeurById(@PathVariable("id_auditeur") int id_auditeur) {
        auditeurService.deleteById(id_auditeur);
        return ResponseEntity.noContent().build();
    }
}

