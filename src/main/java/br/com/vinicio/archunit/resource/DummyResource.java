package br.com.vinicio.archunit.resource;

import br.com.vinicio.archunit.service.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class DummyResource {

    @Autowired
    private DummyService dummyService;

    @GetMapping("/")
    public String getDummyMethod() {
        return dummyService.getDummyMethod();
    }
}