package com.example.library.service.impl;

import com.example.library.exception.PatronNotFoundException;
import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;
import com.example.library.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatronServiceImpl implements PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public List<Patron> getAllPatrons() {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new PatronNotFoundException(id));
    }

    @Override
    @Transactional
    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Override
    @Transactional
    public Patron updatePatron(Long id, Patron patron) {
        return null;
    }

    @Override
    @Transactional
    public void deletePatron(Long id) {
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> new PatronNotFoundException(id));
        patronRepository.delete(patron);
    }

    // Other methods
}
