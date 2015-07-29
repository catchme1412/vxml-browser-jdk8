package com.vxml.core;

import java.net.URISyntaxException;

import org.junit.After;
import org.junit.Test;

public class OrbitzIVRTest {

    private VxmlBrowserDriver driver;

    public void init() throws URISyntaxException {
        driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
    }

    @Test
    public void testExecute() throws URISyntaxException {
        VxmlBrowserDriver driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
        driver.setEntryPointUrl("http://localhost:8585/ivr/testing/sao.vxml");
        driver.start();
        String o = driver.nextOuput();
        System.out.println(o);
        driver.nextInput("1");
        driver.nextInput("1");
        o = driver.nextOuput();
        System.out.println(o);
        o = driver.nextOuput();
        System.out.println(o);
        o = driver.nextOuput();
        System.out.println(o);
        o = driver.nextOuput();
        System.out.println(o);
        o = driver.nextOuput();
        System.out.println(o);
        o = driver.nextOuput();
        System.out.println(o);
        driver.waitForCompletion();
    }
    
    @After
    public void waitForCompletion() {
        if (driver != null) {
            driver.waitForCompletion();
        }
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
        driver.setEntryPointUrl("http://localhost:8585/ivr/testing/sao.vxml");
        driver.start();
        System.out.println("No wait...");
        // String o = driver.nextOuput();
        // System.err.println(o);
        driver.nextInput("1");
//        driver.nextInput("false");
//        driver.nextInput("true");
//        driver.nextInput("false");
//        // o = driver.nextOuput();
        String o;
        int i = 0;
        while ((o = driver.nextOuput()) != null && i++ < 5) {
            System.err.println("OUTPUT:" + o);
        }
        driver.waitForCompletion();
    }

 
}
