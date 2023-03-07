package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Frais;
import fr.m2i.secureAudit.service.FraisService;
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

    @PostMapping("/post")
    public void createFrais(@RequestBody Frais frais) {
        fraisService.save(frais);
    }

    @PutMapping("/put/{id_frais}")
    public ResponseEntity<Frais> updateFrais(@PathVariable int id_frais, @RequestBody Frais frais) {
        Frais existingFrais = fraisService.findById(id_frais);
        if (existingFrais == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            frais.setIdFrais(id_frais);
            fraisService.update(id_frais, frais);
            return new ResponseEntity<>(frais, HttpStatus.OK);
        }
    }

    @DeleteMapping("/del/{id_frais}")
    public void deleteFrais(@PathVariable int id_frais) {
        fraisService.delete(id_frais);
    }
}

