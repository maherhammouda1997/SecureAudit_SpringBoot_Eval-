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

    @PostMapping("/post")
    public ResponseEntity<Audit> addAudit(@RequestBody Audit audit) {
        auditService.save(audit);
        return ResponseEntity.status(HttpStatus.CREATED).body(audit);
    }

    @PutMapping("/put/{id_auditd}")
    public ResponseEntity<Audit> updateAudit(@PathVariable int id_audit, @RequestBody Audit audit) {
        auditService.update(id_audit, audit);
        return ResponseEntity.ok(audit);
    }

    @DeleteMapping("/del/{id_audit}")
    public ResponseEntity<Void> deleteAudit(@PathVariable int id_audit) {
        auditService.delete(id_audit);
        return ResponseEntity.noContent().build();
    }
}

