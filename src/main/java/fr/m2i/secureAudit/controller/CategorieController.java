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

    @GetMapping("/get")
    public ResponseEntity<List<Categorie>> getAllCategories() {
        List<Categorie> categories = categorieService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/get/{id_categorie}")
    public ResponseEntity<?> getCategorieById(@PathVariable int id_categorie) {
        Categorie categorie = categorieService.getById(id_categorie);
        if (categorie != null) {
            return ResponseEntity.ok(categorie);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("categorie not found !");
        }
    }

    @PostMapping("/post")
    public ResponseEntity<String> createCategorie(@RequestBody Categorie categorie) {
        try {
            if (categorie.getLibelle() == null) {
                return ResponseEntity.badRequest().body("bad request :( ");
            }
            categorieService.save(categorie);
            return ResponseEntity.status(HttpStatus.CREATED).body("categorie added");
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put/{id_categorie}")
    public ResponseEntity<String> updateCategorie(@PathVariable int id_categorie, @RequestBody Categorie categorie) {
        try {
            boolean isUpdated = categorieService.update(id_categorie, categorie);
            if (isUpdated) {
                return ResponseEntity.ok("Categorie updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categorie not found");
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/del/{id_categorie}")
    public ResponseEntity<String> deleteCategorie(@PathVariable int id_categorie) {
        boolean isDeleted = categorieService.deleteById(id_categorie);
        if (isDeleted) {
            String message = "Categorie deleted successfully";
            return ResponseEntity.ok(message);
        } else {
            String message = "Categorie not found!";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

}

