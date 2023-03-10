package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Industrie;
import fr.m2i.secureAudit.serviceInterfaces.IndustrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Annotation indiquant que cette classe est un contrôleur REST
@RequestMapping("/industrie") // Annotation pour définir le chemin de base de toutes les URL relatives à cette classe
public class IndustrieController {

    private final IndustrieService industrieService;

    @Autowired // Annotation pour injecter l'instance de IndustrieService
    public IndustrieController(IndustrieService industrieService) {
        this.industrieService = industrieService;
    }

    @GetMapping("/get") // Endpoint pour récupérer toutes les industries
    public ResponseEntity<List<Industrie>> findAll() {
        List<Industrie> industries = industrieService.findAll(); // Appel à la méthode du service pour récupérer toutes les industries
        return new ResponseEntity<>(industries, HttpStatus.OK); // Renvoie la liste des industries avec le code de réponse HTTP 200 (OK)
    }

    @GetMapping("/get/{id_industrie}") // Endpoint pour récupérer une industrie par son ID
    public ResponseEntity<?> findById(@PathVariable int id_industrie) {
        Industrie industrie = industrieService.findById(id_industrie); // Appel à la méthode du service pour récupérer une industrie par son ID
        if (industrie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("industrie not found"); // Si l'industrie n'est pas trouvée, renvoie une réponse HTTP 404 (NOT_FOUND)
        }
        return ResponseEntity.ok(industrie); // Sinon, renvoie l'industrie trouvée avec le code de réponse HTTP 200 (OK)
    }

    @PostMapping("/post") // Endpoint pour ajouter une nouvelle industrie
    public ResponseEntity<?> save(@RequestBody Industrie industrie) {
        try {
            industrieService.save(industrie); // Appel à la méthode du service pour ajouter une nouvelle industrie
            return new ResponseEntity<>("Industrie added successfully", HttpStatus.CREATED); // Renvoie un message de succès avec le code de réponse HTTP 201 (CREATED)
        } catch (IllegalArgumentException e) { // Si la demande est mal formée, renvoie un message d'erreur avec le code de réponse HTTP 400 (BAD_REQUEST)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put/{id_industrie}") // Endpoint pour mettre à jour une industrie par son ID
    public ResponseEntity<?> update(@PathVariable int id_industrie, @RequestBody Industrie industrie) {
        try {
            Industrie existingIndustrie = industrieService.findById(id_industrie); // Appel à la méthode du service pour récupérer l'industrie existante
            if (existingIndustrie == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("industrie not found"); // Si l'industrie n'est pas trouvée, renvoie une réponse HTTP 404 (NOT_FOUND)
            }
            industrieService.update(id_industrie, industrie); // Appel à la méthode du service pour mettre à jour l'industrie
            return ResponseEntity.status(HttpStatus.OK).body("industrie updated successfully"); // Renvoie un message de succès avec le code de réponse HTTP 200 (OK)
        } catch (IllegalArgumentException e) { // Si la demande est mal formée, renvoie un message d'erreur avec le code de réponse HTTP 400 (BAD_REQUEST)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/del/{id_industrie}") // Endpoint pour supprimer une industrie par son ID
    public ResponseEntity<String> delete(@PathVariable int id_industrie) {
        boolean isDeleted = industrieService.delete(id_industrie);
        if (isDeleted) {
            String message = "industrie deleted successfully";
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("industrie not found");
    }
}

