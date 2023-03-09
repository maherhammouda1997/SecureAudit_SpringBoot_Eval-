package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Audit;
import fr.m2i.secureAudit.model.Categorie;
import fr.m2i.secureAudit.model.Frais;
import fr.m2i.secureAudit.serviceInterfaces.AuditService;
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

    @Autowired
    private FraisService fraisService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private CategorieService categorieService;

    @GetMapping("/get")
    public ResponseEntity<List<Frais>> getAllFrais() {
        List<Frais> fraisList = fraisService.findAll();
        return ResponseEntity.ok().body(fraisList);
    }

    @GetMapping("/get/{id_frais}")
    public ResponseEntity<?> getFraisById(@PathVariable int id_frais) {
        Frais frais = fraisService.findById(id_frais);
        if (frais != null) {
            return ResponseEntity.ok(frais);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("frais not found !");
        }
    }

    @PostMapping("/post/{id_audit}/{id_categorie}")
    public ResponseEntity<String> addFrais(@PathVariable int id_audit, @PathVariable int id_categorie ,@RequestBody Frais frais) {
        try {
            Audit audit = auditService.findById(id_audit);
            if (audit == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Audit not found");
            }
            Categorie categorie = categorieService.getById(id_categorie);
            if (categorie == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categorie not found");
            }
            fraisService.addFrais(frais, id_audit, id_categorie);
            return ResponseEntity.status(HttpStatus.CREATED).body("frais added successfully");
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put/{id_frais}")
    public ResponseEntity<String> updateFrais(@PathVariable int id_frais, @RequestBody Frais frais) {
        try {
            frais.setId_frais(id_frais);
            int id_audit = frais.getAudit() != null ? frais.getAudit().getId_audit() : 0;
            int id_categorie = frais.getCategorie() != null ? frais.getCategorie().getId_categorie() : 0;

            Frais existingFrais = fraisService.findById(id_frais);
            if (existingFrais == null) {
                return new ResponseEntity<>("Frais not found", HttpStatus.NOT_FOUND);
            }

            Audit audit = auditService.findById(id_audit);
            if (audit == null) {
                return new ResponseEntity<>("Audit not found", HttpStatus.BAD_REQUEST);
            }

            Categorie categorie = categorieService.getById(id_categorie);
            if (categorie == null) {
                return new ResponseEntity<>("Categorie not found", HttpStatus.BAD_REQUEST);
            }
            frais.setAudit(audit);
            frais.setCategorie(categorie);
            fraisService.update(id_frais, frais);
            return ResponseEntity.ok("Frais updated successfully");
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/del/{id_frais}")
    public ResponseEntity<String> deleteFrais(@PathVariable int id_frais) {
        boolean isDeleted = fraisService.delete(id_frais);
        if (isDeleted) {
            String message = "Frais deleted successfully";
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Frais not found");
        }
    }
}

