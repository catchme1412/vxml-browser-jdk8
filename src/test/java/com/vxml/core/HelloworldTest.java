package com.vxml.core;

import java.net.URISyntaxException;

import junit.framework.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HelloworldTest {

    private VxmlBrowserDriver driver;

    @BeforeTest
    public void init() throws URISyntaxException {
        driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
    }

    @Test
    public void test() throws URISyntaxException {
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/audio.vxml");
        driver.start();
        String o = driver.nextOuput();
        Assert.assertEquals("Audio file demonstration", o);
    }

    @Test
    public void testCondition() throws URISyntaxException, InterruptedException {
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/nestedIf.vxml");
        driver.start();
        System.out.println("No wait...");
        String o = driver.nextOuput();
        driver.nextInput("true");

        o = driver.nextOuput();
        System.out.println(o);

        driver.nextInput("true");
        o = driver.nextOuput();
        System.out.println(o);

        driver.nextInput("false");
        o = driver.nextOuput();
        System.out.println(o);
        Assert.assertEquals("Both if true", o);

    }

    @Test
    public void testSimpleLogicalCondition() throws URISyntaxException, InterruptedException {
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/ifElseIfElseWithNoGrammar.vxml");
        driver.start();
        System.out.println("No wait...");
        String o = driver.nextOuput();
        System.out.println(o);
        driver.nextInput("1");
        o = driver.nextOuput();
        System.out.println(o);
    }

    @AfterTest
    public void waitForCompletion() {
        driver.waitForCompletion();
    }

    // public static void main(String[] args) throws URISyntaxException {
    // VxmlBrowserDriver driver = new VxmlBrowserDriver();
    // VxmlBrowser vxmlBrowser = new VxmlBrowser();
    // driver.setBrowser(vxmlBrowser);
    // driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/ifElseIfElseWithNoGrammar.vxml");
    // driver.start();
    // System.out.println("No wait...");
    // String o = driver.nextOuput();
    // System.out.println(o);
    // driver.nextInput("3");
    // o = driver.nextOuput();
    // System.out.println(o);
    // driver.waitForCompletion();
    // }

    // public static void main(String[] args) throws URISyntaxException {
    // VxmlBrowserDriver driver = new VxmlBrowserDriver();
    // VxmlBrowser vxmlBrowser = new VxmlBrowser();
    // driver.setBrowser(vxmlBrowser);
    // driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/nestedIf.vxml");
    // driver.start();
    // System.out.println("No wait...");
    // String o = driver.nextOuput();
    // System.out.println(o);
    // driver.nextInput("true");
    // o = driver.nextOuput();
    // driver.nextInput("true");
    // driver.nextInput("false");
    // System.out.println(o);
    // driver.waitForCompletion();
    // }

    public static void main(String[] args) throws URISyntaxException {
        VxmlBrowserDriver driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/nestedIf.vxml");
        driver.start();
        System.out.println("No wait...");
//        String o = driver.nextOuput();
//        System.err.println(o);
        driver.nextInput("true");// a
        driver.nextInput("true");// b
        driver.nextInput("true");// c
        driver.nextInput("false");// d
//        o = driver.nextOuput();
        String o;
        int i = 0;
        while ((o = driver.nextOuput()) != null && i++ < 10) {
            System.err.println("OUTPUT:" + o);
        }
        driver.waitForCompletion();
    }
}
