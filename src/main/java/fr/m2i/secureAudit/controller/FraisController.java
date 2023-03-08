package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Frais;
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

    @GetMapping("/get/{id_frais}")
    public ResponseEntity<Frais> getFraisById(@PathVariable int id_frais) {
        Frais frais = fraisService.findById(id_frais);
        if (frais == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(frais, HttpStatus.OK);
        }
    }

    @GetMapping("/get")
    public List<Frais> getAllFrais() {
        return fraisService.findAll();
    }

    @PostMapping("/post/{id_audit}/{id_categorie}")
    public ResponseEntity<String> addFrais(@PathVariable int id_audit, @PathVariable int id_categorie ,@RequestBody Frais frais) {
        fraisService.addFrais(frais, id_audit, id_categorie);
        return ResponseEntity.status(HttpStatus.CREATED).body("frais added successfully");
    }

    @PutMapping("/put/{id_frais}")
    public ResponseEntity<String> updateFrais(@PathVariable int id_frais, @RequestBody Frais frais) {
        Frais existingFrais = fraisService.findById(id_frais);
        if (existingFrais == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            frais.setId_frais(id_frais);
            fraisService.update(id_frais, frais);
            return ResponseEntity.status(HttpStatus.CREATED).body("frais updated successfully")  ;      }
    }

    @DeleteMapping("/del/{id_frais}")
    public void deleteFrais(@PathVariable int id_frais) {
        fraisService.delete(id_frais);
    }
}

