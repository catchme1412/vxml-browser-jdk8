package com.vxml.core;

import java.net.URISyntaxException;

import org.junit.Test;

public class SubdialogTagTest {

    private VxmlBrowserDriver driver;

    @Test
    public void test() throws URISyntaxException {
        driver = new VxmlBrowserDriver();
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        driver.setBrowser(vxmlBrowser);
        driver.setEntryPointUrl("http://localhost:8082/vxmlbrowser/test/subdialog/mainDialog.vxml");
        driver.start();
        String o = driver.nextOuput();
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

    }

}
