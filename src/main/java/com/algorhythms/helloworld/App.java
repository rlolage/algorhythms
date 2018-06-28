package com.algorhythms.helloworld;

/**
 * Hello world!
 *
 */
public class App implements SuperApp {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    @Override
    public String getValue() {
        return "Hello World";
    }
}
