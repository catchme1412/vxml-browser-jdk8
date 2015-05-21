//package com.vxml.core;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//
//import javax.script.ScriptException;
//
//import jdk.nashorn.internal.objects.NativeObject;
//
//import org.testng.Assert;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//
//public class VxmlScriptEngineTest {
//
//    private VxmlScriptEngine vxmlScriptEngine;
//
//    @BeforeTest
//    private void init() {
//        vxmlScriptEngine = new VxmlScriptEngine();
//    }
//
//    @Test
//    public void testEvalNumber() throws ScriptException {
//        Object r = vxmlScriptEngine.eval("1");
//        Assert.assertEquals(r, new Double(1));
//    }
//
//    @Test
//    public void testEvalJson() throws ScriptException {
//        Object r = vxmlScriptEngine.eval("{'key':'value'}");
//        Assert.assertEquals(r.getClass(), NativeObject.class);
//    }
//
//    @Test
//    public void testBoolean() throws ScriptException {
//        Object r = vxmlScriptEngine.eval("true");
//        Assert.assertEquals(r.getClass(), Boolean.class);
//    }
//
//    @Test
//    public void testBooleanString() throws ScriptException {
//        Object r = vxmlScriptEngine.eval("'true'");
//        Assert.assertEquals(r.getClass(), String.class);
//    }
//    
//    @Test
//    public void testScriptFile() throws ScriptException, FileNotFoundException {
//        System.out.println(new File("").getAbsolutePath());
//        Object r = vxmlScriptEngine.executeScript(new File("/opt/orbitz/code/com.vxml.browser/src/main/webapp/js/test.js"));
//        r = vxmlScriptEngine.eval("test()");
//        Assert.assertEquals(r.toString(), "1.0");
//    }
//
//}
