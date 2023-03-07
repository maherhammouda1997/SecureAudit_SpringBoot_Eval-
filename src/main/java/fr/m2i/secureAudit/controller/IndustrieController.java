package fr.m2i.secureAudit.controller;

import fr.m2i.secureAudit.model.Industrie;
import fr.m2i.secureAudit.service.IndustrieService;
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

    @GetMapping("/get/{id_industrie}")
    public ResponseEntity<Industrie> findById(@PathVariable int id_industrie) {
        Industrie industrie = industrieService.findById(id_industrie);
        if (industrie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(industrie, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Industrie>> findAll() {
        List<Industrie> industries = industrieService.findAll();
        return new ResponseEntity<>(industries, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<Void> save(@RequestBody Industrie industrie) {
        industrieService.save(industrie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/get/{id_industrie}")
    public ResponseEntity<Void> update(@PathVariable int id_industrie, @RequestBody Industrie industrie) {
        industrieService.update(id_industrie, industrie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/del/{id_industrie}")
    public ResponseEntity<Void> delete(@PathVariable int id_industrie) {
        industrieService.delete(id_industrie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

