package com.bsg6.chapter04;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

class ThirdObject extends HasData {
    static Object semaphore = null;

    public void init() {
        semaphore = new Object();
    }

    public void dispose() {
        semaphore = null;
    }
}

@ContextConfiguration(locations = "/config-03.xml")
public class TestLifecycle03 {
    @Test
    public void testInitDestroyMethods() {
        ConfigurableApplicationContext context
                =new ClassPathXmlApplicationContext("/config-03.xml");;

        ThirdObject o1 = context.getBean(ThirdObject.class);
        assertNotNull(ThirdObject.semaphore);
        assertEquals(o1.getDatum(), "default");
        context.close();
        assertNull(ThirdObject.semaphore);
    }
}
