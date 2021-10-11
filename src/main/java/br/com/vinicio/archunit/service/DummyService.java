package br.com.vinicio.archunit.service;

import br.com.vinicio.archunit.repository.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyService {

    @Autowired
    private DummyRepository repository;

    public String getDummyMethod() {
        return repository.getDummyMethod();
    }

}