import junit.framework.Assert;

import org.junit.Test;

import com.vxml.core.Output;


public class OrbitzTest {

    private VxmlExecutionDriver driver = new VxmlExecutionDriver();
    
    @Test
    public void test() throws InterruptedException {
        driver.setEntryPontUrl("http://localhost:8585/ivr/testing/development.vxml");
//        driver.setNextBreakpoint("");
        driver.start();
        assertPosSelectionPrompt();
        driver.setNextInput("1");
        assertOrbPrompt();
        driver.setNextInput("1");
        assertWelcomePrompt();
        driver.setNextInput("1");
        Output o = driver.getNextOuput(2l);
        System.out.println(o);
        o = driver.getNextOuput(2l);
        System.out.println(o);
        o = driver.getNextOuput(2l);
        System.out.println(o);
        o = driver.getNextOuput(2l);
        System.out.println(o);
        driver.setNextInput("1");
        o = driver.getNextOuput(2l);
        System.out.println(o);
        o = driver.getNextOuput(2l);
        System.out.println(o);
        o = driver.getNextOuput(2l);
        System.out.println(o);
        o = driver.getNextOuput(2l);
        System.out.println(o);
        o = driver.getNextOuput(2l);
        System.out.println(o);
        o = driver.getNextOuput(2l);
        System.out.println(o);
        o = driver.getNextOuput(2l);
        System.out.println(o);
        o = driver.getNextOuput(2l);
        System.out.println(o);
        driver.setNextInput("1");
        o = driver.getNextOuput(2l);
        System.out.println(o);
        driver.shutdown();
    }

    private void assertWelcomePrompt() throws InterruptedException {
    	Output o = driver.getNextOuput(2l);
//    	audio:http://ivraudio.orbitz.net/audio-new/ORB/en_US/welcome.wav
//    		audio:http://ivraudio.orbitz.net/common-audio/silence_1_sec.wav
//    		audio:http://ivraudio.orbitz.net/audio-new/ORB/en_US/account_lookup.wav
//    		audio:http://ivraudio.orbitz.net/common-audio/silence_1_sec.wav
        Assert.assertEquals("http://ivraudio.orbitz.net/audio-new/ORB/en_US/welcome.wav", o.getOutput());
        o = driver.getNextOuput(2l);
        Assert.assertEquals("http://ivraudio.orbitz.net/common-audio/silence_1_sec.wav", o.getOutput());
        o = driver.getNextOuput(2l);
        Assert.assertEquals("http://ivraudio.orbitz.net/audio-new/ORB/en_US/account_lookup.wav", o.getOutput());
        o = driver.getNextOuput(2l);
        Assert.assertEquals("http://ivraudio.orbitz.net/common-audio/silence_1_sec.wav", o.getOutput());
	}

	private void assertPosSelectionPrompt() throws InterruptedException {
        Output o = driver.getNextOuput(2l);
        Assert.assertEquals("http://ivraudio.orbitz.net/common-audio/WelcomeTest.wav", o.getOutput());
        o = driver.getNextOuput(2l);
        Assert.assertEquals("http://ivraudio.orbitz.net/common-audio/posOptions.wav", o.getOutput());
        o = driver.getNextOuput(2l);
        Assert.assertEquals("http://ivraudio.orbitz.net/common-audio/posOptionForHSSD.wav", o.getOutput());
        o = driver.getNextOuput(2l);
        Assert.assertEquals("TTS:Or  Hotel club 9", o.toString());
	}
	
	private void assertOrbPrompt() throws InterruptedException {
		Output o = driver.getNextOuput(2l);
        Assert.assertEquals("TTS:For Orbitz press 1,", o.toString());
        o = driver.getNextOuput(2l);
        Assert.assertEquals("TTS:, For Orbitz ON EX press 2", o.toString());
        o = driver.getNextOuput(2l);
        Assert.assertEquals("TTS:, for default P O S  press 3", o.toString());
        o = driver.getNextOuput(2l);
        Assert.assertEquals("TTS:D N I S mapping 1 2 3 1 2 3", o.toString());
        o = driver.getNextOuput(2l);
        Assert.assertEquals("TTS:, For Orbitz Expedia press 4", o.toString ());
     }
}
