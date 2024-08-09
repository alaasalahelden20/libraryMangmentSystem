package com.example.library.service;

import com.example.library.model.Patron;
import java.util.List;

public interface PatronService {
    List<Patron> getAllPatrons();
    Patron getPatronById(Long id);
    Patron savePatron(Patron patron);
    Patron updatePatron(Long id, Patron patron);
    void deletePatron(Long id);
}
