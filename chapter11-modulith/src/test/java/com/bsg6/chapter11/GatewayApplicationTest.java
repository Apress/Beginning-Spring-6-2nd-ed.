package com.bsg6.chapter11;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(classes = GatewayApplication.class)
public class GatewayApplicationTest extends AbstractTestNGSpringContextTests {

    @Test
    public void testModulesValid() {
        ApplicationModules.of(GatewayApplication.class).verify();
    }
}
