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
    public List<Audit> getAudit() {
        return auditService.getAudit();
    }

    @GetMapping("/get/{id_audit}")
    public ResponseEntity<Audit> getAuditById(@PathVariable int id_audit) {
        Audit audit = auditService.findById(id_audit);
        if (audit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(audit);
    }

    @PostMapping("/post/{id_industrie}/{id_auditeur}")
    public ResponseEntity<String> addAudit(@PathVariable int id_industrie, @PathVariable int id_auditeur ,@RequestBody Audit audit) {
        auditService.addAudit(audit, id_industrie, id_auditeur);
        return ResponseEntity.status(HttpStatus.CREATED).body("Audit added successfully");
    }

    @PutMapping("/put/{id_audit}")
    public ResponseEntity<String> updateAudit(@PathVariable int id_audit, @RequestBody Audit audit) {
        audit.setId_audit(id_audit);
        int id_industrie = audit.getIndustrie() != null ? audit.getIndustrie().getId_industrie() : 0;
        int id_auditeur = audit.getAuditeur() != null ? audit.getAuditeur().getId_auditeur() : 0;
        String result = auditService.update(audit, id_industrie, id_auditeur);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/del/{id_audit}")
    public ResponseEntity<Void> deleteAudit(@PathVariable int id_audit) {
        auditService.delete(id_audit);
        return ResponseEntity.noContent().build();
    }
}

