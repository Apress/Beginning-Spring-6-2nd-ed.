package com.bsg6.chapter02;

import com.bsg6.chapter02.Greeter;

import java.io.PrintStream;

public class HelloWorldGreeter implements Greeter {

    private PrintStream printStream = System.out;

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void greet() {
        printStream.print("Hello, World!");
    }

}
