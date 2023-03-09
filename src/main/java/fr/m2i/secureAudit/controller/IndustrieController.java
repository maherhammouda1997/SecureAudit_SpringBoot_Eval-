package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Industrie;
import fr.m2i.secureAudit.serviceInterfaces.IndustrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industrie")
public class IndustrieController {

    private final IndustrieService industrieService;

    @Autowired
    public IndustrieController(IndustrieService industrieService) {
        this.industrieService = industrieService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Industrie>> findAll() {
        List<Industrie> industries = industrieService.findAll();
        return new ResponseEntity<>(industries, HttpStatus.OK);
    }

    @GetMapping("/get/{id_industrie}")
    public ResponseEntity<?> findById(@PathVariable int id_industrie) {
        Industrie industrie = industrieService.findById(id_industrie);
        if (industrie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("industrie not found");
        }
        return ResponseEntity.ok(industrie);
    }

    @PostMapping("/post")
    public ResponseEntity<?> save(@RequestBody Industrie industrie) {
        try {
            industrieService.save(industrie);
            return new ResponseEntity<>("Industrie added successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put/{id_industrie}")
    public ResponseEntity<?> update(@PathVariable int id_industrie, @RequestBody Industrie industrie) {
        try {
            Industrie existingIndustrie = industrieService.findById(id_industrie);
            if (existingIndustrie == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("industrie not found");
            }
            industrieService.update(id_industrie, industrie);
            return ResponseEntity.status(HttpStatus.OK).body("industrie updated successfully");
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/del/{id_industrie}")
    public ResponseEntity<String> delete(@PathVariable int id_industrie) {
        boolean isDeleted = industrieService.delete(id_industrie);
        if (isDeleted) {
            String message = "industrie deleted successfully";
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("industrie not found");
    }
}

