package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Categorie;
import fr.m2i.secureAudit.serviceInterfaces.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("/categorie") // Indique l'URL de base pour toutes les requêtes
public class CategorieController {

    @Autowired // Injecte le service de gestion des catégories dans ce contrôleur
    private CategorieService categorieService;

    // Endpoint pour récupérer toutes les catégories
    @GetMapping("/get")
    public ResponseEntity<List<Categorie>> getAllCategories() {
        List<Categorie> categories = categorieService.getCategories(); // Appelle la méthode du service pour récupérer toutes les catégories
        return ResponseEntity.ok(categories); // Retourne les catégories avec un statut HTTP 200 OK
    }

    // Endpoint pour récupérer une catégorie par son ID
    @GetMapping("/get/{id_categorie}")
    public ResponseEntity<?> getCategorieById(@PathVariable int id_categorie) {
        Categorie categorie = categorieService.getById(id_categorie); // Appelle la méthode du service pour récupérer la catégorie correspondant à l'ID
        if (categorie != null) { // Si la catégorie existe
            return ResponseEntity.ok(categorie); // Retourne la catégorie avec un statut HTTP 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("categorie not found !"); // Retourne un message d'erreur avec un statut HTTP 404 NOT FOUND
        }
    }

    // Endpoint pour créer une nouvelle catégorie
    @PostMapping("/post")
    public ResponseEntity<String> createCategorie(@RequestBody Categorie categorie) {
        try {
            categorieService.save(categorie); // Appelle la méthode du service pour sauvegarder la nouvelle catégorie
            return ResponseEntity.status(HttpStatus.CREATED).body("categorie added"); // Retourne un message de succès avec un statut HTTP 201 CREATED
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Retourne un message d'erreur avec un statut HTTP 400 BAD REQUEST
        }
    }

    // Endpoint pour mettre à jour une catégorie existante
    @PutMapping("/put/{id_categorie}")
    public ResponseEntity<String> updateCategorie(@PathVariable int id_categorie, @RequestBody Categorie categorie) {
        try {
            boolean isUpdated = categorieService.update(id_categorie, categorie); // Appelle la méthode du service pour mettre à jour la catégorie correspondant à l'ID
            if (isUpdated) { // Si la catégorie a été mise à jour avec succès
                return ResponseEntity.ok("Categorie updated successfully"); // Retourne un message de succès avec un statut HTTP 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categorie not found"); // Retourne un message d'erreur avec un statut HTTP 404 NOT FOUND
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Retourne un message d'erreur avec un statut HTTP 400 BAD REQUEST
        }
    }

    // Endpoint pour supprimer une catégorie existante
    @DeleteMapping("/del/{id_categorie}")
    public ResponseEntity<String> deleteCategorie(@PathVariable int id_categorie) {
        boolean isDeleted = categorieService.deleteById(id_categorie); // Appelle la méthode du service pour supprimer la catégorie correspondant à l'ID
        if (isDeleted) { // Si la catégorie a été supprimée avec succès
            String message = "Categorie deleted successfully";
            return ResponseEntity.ok(message); // Retourne un message de confirmation
        } else {
            String message = "Categorie not found!";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message); // Retourne un message not found
        }
    }

}

