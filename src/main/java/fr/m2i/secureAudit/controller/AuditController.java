package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Audit;
import fr.m2i.secureAudit.serviceInterfaces.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping("/get")
    public ResponseEntity<List<Audit>> getAudit() {
        List<Audit> audits = auditService.getAudit();
        return new ResponseEntity<List<Audit>>(audits, HttpStatus.OK);
    }

    @GetMapping("/get/{id_audit}")
    public ResponseEntity<?> getAuditById(@PathVariable int id_audit) {
        Audit audit = auditService.findById(id_audit);
        if (audit == null) {
            String message = "Audit with ID " + id_audit + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(audit);
    }

    @PostMapping("/post/{id_industrie}/{id_auditeur}")
    public ResponseEntity<String> addAudit(@PathVariable int id_industrie, @PathVariable int id_auditeur ,@RequestBody Audit audit) {
        try {
            Audit addedAudit = auditService.addAudit(audit, id_industrie, id_auditeur);
            return new ResponseEntity<>("Audit added successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put/{id_audit}")
    public ResponseEntity<String> updateAudit(@PathVariable int id_audit, @RequestBody Audit audit) {
        audit.setId_audit(id_audit);
        int id_industrie = audit.getIndustrie() != null ? audit.getIndustrie().getId_industrie() : 0;
        int id_auditeur = audit.getAuditeur() != null ? audit.getAuditeur().getId_auditeur() : 0;
        Audit existingAudit = auditService.findById(id_audit);
        if (existingAudit == null) {
            return new ResponseEntity<>("Audit not found", HttpStatus.NOT_FOUND);
        }
        String result = auditService.update(audit, id_industrie, id_auditeur);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/del/{id_audit}")
    public ResponseEntity<String> deleteAudit(@PathVariable int id_audit) {
        boolean isDeleted = auditService.delete(id_audit);
        if (isDeleted) {
            String message = "Audit deleted successfully";
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Audit not found");
        }
    }
}

