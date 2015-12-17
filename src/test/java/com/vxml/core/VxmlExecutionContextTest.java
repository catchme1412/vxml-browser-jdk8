package com.vxml.core;

import org.junit.Before;
import org.junit.Test;

public class VxmlExecutionContextTest {
    
//    VxmlExecutionContext vxmlExecutionContext;
    VxmlBrowser browser;
    
    @Before
    public void init() {
        browser = new VxmlBrowser();
    }
    @Test
    public void testAssignVar() {
        browser.getVxmlExecutionContext().assignVar("a", 10);
        Object r = browser.getVxmlExecutionContext().getScriptVar("a");
        System.out.println(r);
    }
    
    
    @Test
    public void testAssignVarInSubdialog() {
        browser.getVxmlExecutionContext().executeScript("var menuResults = {}");
//        AbstractTag.markSubdialog("menuResults");
        Object t = VxmlBrowser.getVxmlExecutionContext().executeScript(VxmlScriptEngine.getSubdialogNameKey());
        browser.getVxmlExecutionContext().assignVar("a", 10);
        Object r = browser.getVxmlExecutionContext().getScriptVar("a");
        System.out.println(r);
    }
    
    @Test
    public void testAssignAndExecute() {
        browser.getVxmlExecutionContext().assignVar("a", 10);
        Object r = browser.getVxmlExecutionContext().executeScript("a");
        System.out.println(r);
    }
    
    @Test
    public void testAssignAndExecuteStringArg() {
        browser.getVxmlExecutionContext().assignVar("a", "10");
        Object r = browser.getVxmlExecutionContext().executeScript("a");
        System.out.println(r);
    }
    
    @Test
    public void testAssignAndExecuteStringArgWithProperty() {
        browser.getVxmlExecutionContext().executeScript("var a = {}");
        browser.getVxmlExecutionContext().assignVar("a.b", "10");
        Object r = browser.getVxmlExecutionContext().executeScript("a.b");
        System.out.println(r);
    }
    
    @Test
    public void testAssignAndGetExpression() {
        browser.getVxmlExecutionContext().executeScript("var a = {}");
        browser.getVxmlExecutionContext().assignVar("a.b", "10 + 20");
        Object r = browser.getVxmlExecutionContext().getScriptVar("a.b");
        System.out.println(r);
    }
    
    @Test
    public void testAssignAndExecuteWithSubdialog() {
        browser.getVxmlExecutionContext().executeScript("var menuResults = {}");
//        AbstractTag.markSubdialog("menuResults");
        browser.getVxmlExecutionContext().assignVar(VxmlScriptEngine.getSubdialogNameKey(), "'string'");
        Object r = browser.getVxmlExecutionContext().executeScript(VxmlScriptEngine.getSubdialogNameKey());
        System.out.println(r);
    }
}

