package com.vxml.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import jdk.nashorn.internal.objects.NativeObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VxmlScriptEngineTest {

    private VxmlScriptEngine vxmlScriptEngine;

    @Before
    public void init() {
        vxmlScriptEngine = new VxmlScriptEngine();
    }

    @Test
    public void testEvalNumber() throws ScriptException {
        Object r = vxmlScriptEngine.eval("1");
        Assert.assertEquals(r, new Double(1));
    }

    @Test
    public void testEvalJson() throws ScriptException {
        Object r = vxmlScriptEngine.eval("{'key':'value'}");
        Assert.assertEquals(r.getClass(), NativeObject.class);
    }

    @Test
    public void testBoolean() throws ScriptException {
        Object r = vxmlScriptEngine.eval("true");
        Assert.assertEquals(r.getClass(), Boolean.class);
    }

    @Test
    public void testBooleanString() throws ScriptException {
        Object r = vxmlScriptEngine.eval("'true'");
        Assert.assertEquals(r.getClass(), String.class);
    }

    @Test
    public void testScriptFile() throws ScriptException, FileNotFoundException {
        System.out.println(new File("").getAbsolutePath());
        Object r = vxmlScriptEngine.executeScript(new File(
                "/opt/orbitz/code/com.vxml.browser/src/main/webapp/js/test.js"));
        r = vxmlScriptEngine.eval("test()");
        Assert.assertEquals(r.toString(), "1.0");
        r = vxmlScriptEngine.executeScript(new File(
                "/opt/orbitz/code/web-ivr/src/main/webapp/ivr/common/js/parseXmlToObject.js"));
        r = vxmlScriptEngine.eval("parseXmlToObject()");
        Assert.assertEquals(r.toString(), "1.0");

    }

    @Test
    public void testScriptExecuteAndVarAccess() {

        vxmlScriptEngine.eval("var a = 1");
        vxmlScriptEngine.eval("var b = a");
        Object r = vxmlScriptEngine.getScriptVar("b");
        Assert.assertEquals(new Double(1d), r);
    }

    @Test
    public void testVarWithQuotes() {
        try {

            vxmlScriptEngine.eval("var a = '';");
            vxmlScriptEngine.eval("var menuResults = {}");
            vxmlScriptEngine.eval("menuResults.holdMusic = {}");
            vxmlScriptEngine
                    .eval("var menuResults.holdMusic = 'http://audio.en-US.tellme.com/common-audio/intellipause.wav'");
            Assert.fail("Should not reach here");
        } catch (Exception e) {

        }
    }

    @Test
    public void testVarWithJavascriptExprn() {
        vxmlScriptEngine.eval("function foo(arg){return arg;}");
        vxmlScriptEngine.eval("var r = foo (12);");
        Object r = vxmlScriptEngine.eval("r");
        System.out.println(r);
    }

    @Test
    public void testVarWithJavascriptExprnUsingMap() {
        vxmlScriptEngine.eval("function foo(arg){return arg;}");
        vxmlScriptEngine.assignScriptVar("a", "foo(12)");
        Object r = vxmlScriptEngine.eval("a");
        Assert.assertEquals(new Double(12), r);
    }

    @Test
    public void testVarWithJavascriptExprnUsingMap2() {
        vxmlScriptEngine.eval("function foo(arg){return arg;}");
        vxmlScriptEngine.assignScriptVar("a", "foo(12)");
        Object r = vxmlScriptEngine.eval("a");
        System.out.println(r);
    }

    @Test
    public void testAssign() {
        vxmlScriptEngine.assignScriptVar("a", 10);
        vxmlScriptEngine.assignScriptVar("b", "a");
        Object r = vxmlScriptEngine.getScriptVar("b");
        System.out.println(r);
    }

    @Test
    public void testExecuteAndGet() {
        vxmlScriptEngine.assignScriptVar("a", "string");
        Object r = vxmlScriptEngine.getScriptVar("a");
    }

    @Test
    public void testExeucuteStringVal() {
        vxmlScriptEngine.assignScriptVar("a", 10);
        vxmlScriptEngine.assignScriptVar("a.b", "20");
        Object r = vxmlScriptEngine.getScriptVar("a.b");
        System.out.println(r);
    }

    @Test
    public void testScript() throws NoSuchMethodException, ScriptException {
        // // vxmlScriptEngine.eval("function foo(arg){return arg;}");
        // // Object r = vxmlScriptEngine.eval("foo()");
        Object r = vxmlScriptEngine.executeScriptFile("http://localhost:8585/ivr/common/js/parseXmlToObject.js");
        //
        Object r1 = vxmlScriptEngine
                .invokeFunction(
                        "parseXmlToObject",
                        "<?xml version='1.0' encoding='UTF-8'?><TripSummaryList><trip_summary><trip_id>4248861</trip_id><trip_locator>TBEBUK6164898144</trip_locator><name>Nairobi Fri, 26 Sep 2008 </name><member_id>5930011</member_id><pos_code>EBUK</pos_code><create_date><year>2008</year><month>6</month><day>18</day><hour>17</hour><minute>6</minute><second>31</second><millis>0</millis><time_zone><id>UTC</id></time_zone></create_date><product><bundle_locator>BBEBUK5408856374</bundle_locator><reservation_date><year>2008</year><month>9</month><day>24</day><hour>16</hour><minute>35</minute><second>7</second><millis>0</millis><time_zone><id>UTC</id></time_zone></reservation_date><product_type>AIR</product_type><product_locator>AP213201P692PGE4</product_locator><status>PRODUCT_ACTIVE</status><air_itinerary><marketing_carrier_code>KL</marketing_carrier_code><traveler><first_name>ROBERT</first_name><last_name>LITTLE</last_name></traveler><slice><origin><id>34639</id><name>MME</name><code>MME</code><time_zone><id>Europe/London</id></time_zone><city_code>Teesside</city_code><state_province_code></state_province_code><country_code>GB</country_code><city_name>Teesside</city_name><country_name>United Kingdom</country_name></origin><destination><id>34877</id><name>NBO</name><code>NBO</code><time_zone><id>Africa/Nairobi</id></time_zone><city_code>Nairobi</city_code><state_province_code></state_province_code><country_code>KE</country_code><city_name>Nairobi</city_name><country_name>Kenya</country_name></destination><depart_time><year>2008</year><month>9</month><day>26</day><hour>17</hour><minute>35</minute><second>0</second><millis>0</millis><time_zone><id>Europe/London</id></time_zone></depart_time><arrival_time><year>2008</year><month>9</month><day>27</day><hour>6</hour><minute>35</minute><second>0</second><millis>0</millis><time_zone><id>Africa/Nairobi</id></time_zone></arrival_time></slice><slice><origin><id>34877</id><name>NBO</name><code>NBO</code><time_zone><id>Africa/Nairobi</id></time_zone><city_code>Nairobi</city_code><state_province_code></state_province_code><country_code>KE</country_code><city_name>Nairobi</city_name><country_name>Kenya</country_name></origin><destination><id>34639</id><name>MME</name><code>MME</code><time_zone><id>Europe/London</id></time_zone><city_code>Teesside</city_code><state_province_code></state_province_code><country_code>GB</country_code><city_name>Teesside</city_name><country_name>United Kingdom</country_name></destination><depart_time><year>2008</year><month>10</month><day>11</day><hour>8</hour><minute>50</minute><second>0</second><millis>0</millis><time_zone><id>Africa/Nairobi</id></time_zone></depart_time><arrival_time><year>2008</year><month>10</month><day>11</day><hour>21</hour><minute>50</minute><second>0</second><millis>0</millis><time_zone><id>Europe/London</id></time_zone></arrival_time></slice></air_itinerary><purchase_locator_code>PBEBUK5258729484</purchase_locator_code></product><trip_platform>GLOBAL_PLATFORM</trip_platform><travel_end_date><year>2008</year><month>10</month><day>11</day><hour>20</hour><minute>50</minute><second>0</second><millis>0</millis><time_zone><id>UTC</id></time_zone></travel_end_date></trip_summary><summary_range><start>1</start><end>1</end><total_count>1</total_count></summary_range></TripSummaryList>");
        ScriptObjectMirror mirror = (ScriptObjectMirror) r1;

        System.out.println(mirror.getClassName() + ": " + Arrays.toString(mirror.getOwnKeys(true)));

        String key1 = mirror.getOwnKeys(true)[0];
        System.out.println("key1:" + key1);
        Object r2 = mirror.getMember(key1);
        ScriptObjectMirror mirror2 = (ScriptObjectMirror) r2;

        System.out.println(mirror2.getClassName() + ": " + Arrays.toString(mirror2.getOwnKeys(true)));

        String key2 = mirror2.getOwnKeys(true)[0];
        System.out.println("key2:" + key2);
        Object r3 = mirror2.getMember(key2);
        System.out.println(r3);
        Assert.assertEquals("4248861", r3);
    }

    @Test
    public void testScriptVxmlStyle() throws NoSuchMethodException, ScriptException {
        Object r = vxmlScriptEngine.executeScriptFile("http://localhost:8585/ivr/common/js/parseXmlToObject.js");
        String exp = "var tripObj = parseXmlToObject('<?xml version=1.0 encoding=UTF-8?><TripSummaryList><trip_summary><trip_id>4248861</trip_id><trip_locator>TBEBUK6164898144</trip_locator><name>Nairobi Fri, 26 Sep 2008 </name><member_id>5930011</member_id><pos_code>EBUK</pos_code><create_date><year>2008</year><month>6</month><day>18</day><hour>17</hour><minute>6</minute><second>31</second><millis>0</millis><time_zone><id>UTC</id></time_zone></create_date><product><bundle_locator>BBEBUK5408856374</bundle_locator><reservation_date><year>2008</year><month>9</month><day>24</day><hour>16</hour><minute>35</minute><second>7</second><millis>0</millis><time_zone><id>UTC</id></time_zone></reservation_date><product_type>AIR</product_type><product_locator>AP213201P692PGE4</product_locator><status>PRODUCT_ACTIVE</status><air_itinerary><marketing_carrier_code>KL</marketing_carrier_code><traveler><first_name>ROBERT</first_name><last_name>LITTLE</last_name></traveler><slice><origin><id>34639</id><name>MME</name><code>MME</code><time_zone><id>Europe/London</id></time_zone><city_code>Teesside</city_code><state_province_code></state_province_code><country_code>GB</country_code><city_name>Teesside</city_name><country_name>United Kingdom</country_name></origin><destination><id>34877</id><name>NBO</name><code>NBO</code><time_zone><id>Africa/Nairobi</id></time_zone><city_code>Nairobi</city_code><state_province_code></state_province_code><country_code>KE</country_code><city_name>Nairobi</city_name><country_name>Kenya</country_name></destination><depart_time><year>2008</year><month>9</month><day>26</day><hour>17</hour><minute>35</minute><second>0</second><millis>0</millis><time_zone><id>Europe/London</id></time_zone></depart_time><arrival_time><year>2008</year><month>9</month><day>27</day><hour>6</hour><minute>35</minute><second>0</second><millis>0</millis><time_zone><id>Africa/Nairobi</id></time_zone></arrival_time></slice><slice><origin><id>34877</id><name>NBO</name><code>NBO</code><time_zone><id>Africa/Nairobi</id></time_zone><city_code>Nairobi</city_code><state_province_code></state_province_code><country_code>KE</country_code><city_name>Nairobi</city_name><country_name>Kenya</country_name></origin><destination><id>34639</id><name>MME</name><code>MME</code><time_zone><id>Europe/London</id></time_zone><city_code>Teesside</city_code><state_province_code></state_province_code><country_code>GB</country_code><city_name>Teesside</city_name><country_name>United Kingdom</country_name></destination><depart_time><year>2008</year><month>10</month><day>11</day><hour>8</hour><minute>50</minute><second>0</second><millis>0</millis><time_zone><id>Africa/Nairobi</id></time_zone></depart_time><arrival_time><year>2008</year><month>10</month><day>11</day><hour>21</hour><minute>50</minute><second>0</second><millis>0</millis><time_zone><id>Europe/London</id></time_zone></arrival_time></slice></air_itinerary><purchase_locator_code>PBEBUK5258729484</purchase_locator_code></product><trip_platform>GLOBAL_PLATFORM</trip_platform><travel_end_date><year>2008</year><month>10</month><day>11</day><hour>20</hour><minute>50</minute><second>0</second><millis>0</millis><time_zone><id>UTC</id></time_zone></travel_end_date></trip_summary><summary_range><start>1</start><end>1</end><total_count>1</total_count></summary_range></TripSummaryList>');";
        vxmlScriptEngine.eval(exp);
        Object r1 = vxmlScriptEngine.getScriptVar("tripObj");
        ScriptObjectMirror mirror = (ScriptObjectMirror) r1;

        System.out.println(mirror.getClassName() + ": " + Arrays.toString(mirror.getOwnKeys(true)));
        System.out.println(r1);
    }

    @Test
    public void testInvokeFunction() throws ScriptException, FileNotFoundException, NoSuchMethodException {
        Object r = vxmlScriptEngine.executeScript(new File(
                "/opt/orbitz/code/com.vxml.browser/src/main/webapp/js/test.js"));
        r = vxmlScriptEngine.invokeFunction("test", null);
        ScriptObjectMirror mirror = (ScriptObjectMirror) r;
        System.out.println(mirror.getClassName() + ": " + Arrays.toString(mirror.getOwnKeys(true)));
        Assert.assertEquals(1, r);
    }
}
