package br.com.vinicio.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

public class MyArchitectureTest {
    @Test
    public void some_architecture_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("br.com.vinicio.archunit");

//        ArchRule rule = classes()... // see next section

//        rule.check(importedClasses);
}
