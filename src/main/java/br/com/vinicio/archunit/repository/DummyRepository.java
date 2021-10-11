package br.com.vinicio.archunit.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DummyRepository {

    public String getDummyMethod(){
        return "Dummy response";
    }
}