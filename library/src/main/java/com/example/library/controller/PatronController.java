package com.example.library.controller;

import com.example.library.exception.PatronNotFoundException;
import com.example.library.model.Patron;
import com.example.library.service.PatronService;
import com.example.library.dto.PatronDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {
        try {
            Patron patron = patronService.getPatronById(id);
            return ResponseEntity.ok(patron);
        } catch (PatronNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Patron> createPatron(@Valid @RequestBody PatronDTO patronDTO) {
        Patron patron=new Patron();
        patron.setName(patronDTO.getName());
        patron.setContactInformation(patron.getContactInformation());
        Patron CreatedPatron= patronService.savePatron(patron);

        return ResponseEntity.ok(CreatedPatron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @Valid @RequestBody PatronDTO patronDTO) {
        try {

            Patron patron=new Patron();
            patron.setName(patronDTO.getName());
            patron.setContactInformation(patron.getContactInformation());
            Patron updatedPatron = patronService.updatePatron(id, patron);
            return ResponseEntity.ok(updatedPatron);
        } catch (PatronNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        try {
            patronService.deletePatron(id);
            return ResponseEntity.noContent().build();
        } catch (PatronNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
