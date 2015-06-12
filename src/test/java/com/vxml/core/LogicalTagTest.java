package com.vxml.core;

import java.net.URISyntaxException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogicalTagTest {

    private VxmlBrowserDriver driver;

    @Before
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
        driver.waitForCompletion();
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
        driver.waitForCompletion();

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
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/nestedIf.vxml");
        driver.start();
        System.out.println("No wait...");
        // String o = driver.nextOuput();
        // System.err.println(o);
        driver.nextInput("true");// a
        driver.nextInput("false");// b
        driver.nextInput("true");// c
        driver.nextInput("false");// d
        // o = driver.nextOuput();
        String o;
        int i = 0;
        while ((o = driver.nextOuput()) != null && i++ < 5) {
            System.err.println("OUTPUT:" + o);
        }
        driver.waitForCompletion();
    }

    @Test
    public void testNestedIf() throws URISyntaxException {
        VxmlBrowserDriver driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/nestedIf.vxml");
        driver.start();
        System.out.println("No wait...");
        // String o = driver.nextOuput();
        // System.err.println(o);
        driver.nextInput("true");// a
        driver.nextInput("true");// b
        driver.nextInput("true");// c
        driver.nextInput("true");// d
        // o = driver.nextOuput();
        String o;
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
        o = driver.nextOuput();
        Assert.assertEquals("Both if true", o);
        driver.waitForCompletion();
        
    }
    
    
    @Test
    public void testNestedElseIf() throws URISyntaxException {
        VxmlBrowserDriver driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/nestedIf.vxml");
        driver.start();
        System.out.println("No wait...");
        // String o = driver.nextOuput();
        // System.err.println(o);
        driver.nextInput("true");// a
        driver.nextInput("false");// b
        driver.nextInput("true");// c
        driver.nextInput("true");// d
        // o = driver.nextOuput();
        String o;
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
       
        Assert.assertEquals("Nested else if is true", o);

    }
    
    @Test
    public void testNestedElse() throws URISyntaxException {
        VxmlBrowserDriver driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/nestedIf.vxml");
        driver.start();
        System.out.println("No wait...");
        // String o = driver.nextOuput();
        // System.err.println(o);
        driver.nextInput("true");// a
        driver.nextInput("false");// b
        driver.nextInput("false");// c
        driver.nextInput("true");// d
        // o = driver.nextOuput();
        String o;
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
       
        Assert.assertEquals("Nested else is true", o);

    }
    
    
    @Test
    public void testOuterElseIf() throws URISyntaxException {
        VxmlBrowserDriver driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/nestedIf.vxml");
        driver.start();
        System.out.println("No wait...");
        // String o = driver.nextOuput();
        // System.err.println(o);
        driver.nextInput("false");// a
        driver.nextInput("true");// b
        driver.nextInput("false");// c
        driver.nextInput("true");// d
        // o = driver.nextOuput();
        String o;
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
        
        Assert.assertEquals("outer else if condition is true", o);

    }
    
    
    @Test
    public void testOuterElse() throws URISyntaxException {
        VxmlBrowserDriver driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/nestedIf.vxml");
        driver.start();
        System.out.println("No wait...");
        // String o = driver.nextOuput();
        // System.err.println(o);
        driver.nextInput("false");// a
        driver.nextInput("true");// b
        driver.nextInput("false");// c
        driver.nextInput("false");// d
        // o = driver.nextOuput();
        String o;
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
        
        Assert.assertEquals("Outer else condition is true", o);

    }
    
    
    
    @Test
    public void testDeeplyNestedInnerMostIf() throws URISyntaxException {
        VxmlBrowserDriver driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/deeplyNestedIf.vxml");
        driver.start();
        System.out.println("No wait...");
        // String o = driver.nextOuput();
        // System.err.println(o);
        driver.nextInput("true");// a
        driver.nextInput("true");// b
        driver.nextInput("true");// innerMostIf
        driver.nextInput("false");// c
        driver.nextInput("false");// d
        // o = driver.nextOuput();
        String o;
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
        o = driver.nextOuput();
        Assert.assertEquals("Both if true", o);
        o = driver.nextOuput();
        Assert.assertEquals("Inner Most if is also true", o);
    }
    
    @Test
    public void testDeeplyNestedInnerMostElse() throws URISyntaxException {
        VxmlBrowserDriver driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
        driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/deeplyNestedIf.vxml");
        driver.start();
        System.out.println("No wait...");
        // String o = driver.nextOuput();
        // System.err.println(o);
        driver.nextInput("true");// a
        driver.nextInput("true");// b
        driver.nextInput("false");// innerMostIf
        driver.nextInput("false");// c
        driver.nextInput("false");// d
        // o = driver.nextOuput();
        String o;
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        o = driver.nextOuput();
        System.err.println("OUTPUT:" + o);
        o = driver.nextOuput();
        Assert.assertEquals("Both if true", o);
        o = driver.nextOuput();
        Assert.assertEquals("Inner Most else is true", o);
    }
}
