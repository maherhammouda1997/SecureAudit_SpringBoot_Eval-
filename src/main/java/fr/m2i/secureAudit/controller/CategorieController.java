package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Categorie;
import fr.m2i.secureAudit.serviceInterfaces.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorie")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping("/get/{id_categorie}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable int id_categorie) {
        Categorie categorie = categorieService.findById(id_categorie);
        if (categorie != null) {
            return ResponseEntity.ok(categorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get")
    public List<Categorie> getAllCategories() {
        return categorieService.findAll();
    }

    @PostMapping("/post")
    public ResponseEntity<Void> createCategorie(@RequestBody Categorie categorie) {
        categorieService.save(categorie);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/put/{id_categorie}")
    public ResponseEntity<Void> updateCategorie(@PathVariable int id_categorie, @RequestBody Categorie categorie) {
        categorieService.update(id_categorie, categorie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/del/{id_categorie}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable int id_categorie) {
        categorieService.delete(id_categorie);
        return ResponseEntity.noContent().build();
    }
}

