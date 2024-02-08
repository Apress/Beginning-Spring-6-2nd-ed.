package com.bsg6.chapter11;

import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.testng.annotations.Test;

public class DocumentationTests {

    ApplicationModules modules = ApplicationModules.of(GatewayApplication.class);

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }
}
