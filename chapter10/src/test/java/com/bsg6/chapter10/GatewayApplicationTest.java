package com.bsg6.chapter10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.AssertJUnit.assertNotNull;

// tag::declaration[]
@SpringBootTest(classes = GatewayApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GatewayApplicationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }
    // end::declaration[]

    @Test
    public void contextLoads() {
        assertNotNull(mockMvc);
    }

// tag::tests[]
    @Test
    public void testDashboardRequiresLogin() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDashboardAfterLogin() throws Exception {
        this.mockMvc.perform(get("/")
                .with(SecurityMockMvcRequestPostProcessors
                        .user("user")
                        .password("password")))
                .andExpect(status().isOk());
    }
    // end::tests[]
}
