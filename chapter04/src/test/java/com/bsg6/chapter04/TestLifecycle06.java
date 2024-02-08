package com.bsg6.chapter04;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Component
class SeventhObject extends HasData {
    static Object semaphore = null;

    @PostConstruct
    public void initialize() throws Exception {
        semaphore = new Object();
    }

    @PreDestroy
    public void dispose() throws Exception {
        semaphore = null;

    }
}

public class TestLifecycle06 {

    @Test
    public void testInitDestroyMethods() {
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext("/annotated-06.xml");

        SeventhObject o1 = context.getBean(SeventhObject.class);
        assertNotNull(SeventhObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        context.close();
        assertNull(EighthObject.semaphore);
    }

}
