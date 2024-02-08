package com.bsg6.chapter02;

import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.testng.Assert.assertEquals;

public class GreeterTest {
    @Test
    public void testHelloWorld() {
        Greeter greeter = new HelloWorldGreeter();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(baos,
                true,
                StandardCharsets.UTF_8)) {
            greeter.setPrintStream(ps);
            greeter.greet();
        }
        String data = baos.toString(StandardCharsets.UTF_8);
        assertEquals(data, "Hello, World!");
    }
}
