package com.vxml.core.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class ConsoleInputReadTask implements Callable<String> {
    public String call() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("input>>");
        String input;
        do {
            try {
                // wait until we have data to complete a readLine()
                while (!br.ready()) {
                    Thread.sleep(20);
                }
                input = br.readLine();
            } catch (InterruptedException e) {
//                System.err.println("No input received!");
                return null;
            }
        } while ("".equals(input));
//        System.out.println("Thank You for providing input!");
        return input;
    }
    
    public static void main(String[] args) throws IOException {
        new ConsoleInputReadTask().call();
    }
}