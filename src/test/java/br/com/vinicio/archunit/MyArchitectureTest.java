package br.com.vinicio.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


public class MyArchitectureTest {

    @Test
    public void services_should_only_be_accessed_by_resources_and_services() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("br.com.vinicio.archunit");
        ArchRule myRule = classes()
                .that().resideInAPackage("..service..")
//                .that().areAnnotatedWith(Service.class)
                .should().onlyBeAccessed().byAnyPackage("..resource..", "..service..");
        myRule.check(importedClasses);
    }

    @Test
    public void repositories_should_only_be_accessed_by_services() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("br.com.vinicio.archunit");
        ArchRule myRule = classes()
                .that().resideInAPackage("..repository..")
                .should().onlyBeAccessed().byAnyPackage("..service..");
        myRule.check(importedClasses);
    }

    @Test
    public void should_classes_must_not_be_suffixed_with_DTO() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("br.com.vinicio.archunit");
        ArchRule myRule = noClasses()
                .should().haveSimpleNameEndingWith("Dto")
                        .orShould().haveSimpleNameEndingWith("DTO");
        myRule.check(importedClasses);
    }

    @Test
    public void should_not_violate_layers() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("br.com.vinicio.archunit");

        Architectures.LayeredArchitecture arch = layeredArchitecture()
                // Define layers
                .layer("Resource").definedBy("..resource..")
                .layer("Service").definedBy("..service..")
                .layer("Repository").definedBy("..repository..")
                // Add constraints
                .whereLayer("Resource").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Resource", "Service")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");
        arch.check(importedClasses);

    }

}
