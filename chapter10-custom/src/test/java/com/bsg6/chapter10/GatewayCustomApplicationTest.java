package com.bsg6.chapter10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.AssertJUnit.assertNotNull;

// tag::declaration[]
@SpringBootTest(classes = GatewayCustomApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GatewayCustomApplicationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        System.out.println(webApplicationContext);
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
    public void testHomepageRequiresLogin() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithUserDetails()
    public void testDashboardAfterLoginAsUser() throws Exception {
        this.mockMvc.perform(
            get("/dashboard")
        ).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("admin")
    public void testDashboardAfterLoginAsAdmin() throws Exception {
        this.mockMvc.perform(
            get("/admin_dashboard")
        ).andExpect(status().isOk());
    }
    // end::tests[]
}
