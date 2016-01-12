import org.junit.After;
import org.junit.Test;

import com.vxml.core.Output;

public class ChoiceTest {

	private VxmlExecutionDriver driver = new VxmlExecutionDriver();

	@Test
	public void test() throws InterruptedException {
		driver.setEntryPontUrl("http://localhost:8082/vxmlbrowser/test/choice.vxml");
		driver.setEntryPontUrl("http://localhost:8585/ivr/testing/sao.vxml");
		driver.start();
		driver.setNextInput("1");
		Output o = driver.getNextOutput();
		System.out.println(">>>>>>>"+o.getOutput().replaceAll("\r", " ").replaceAll("\n", " "));
		o = driver.getNextOutput();
		System.out.println(">>>>>>>"+o.getOutput().replaceAll("\r", " ").replaceAll("\n", " "));
		o = driver.getNextOutput();
		System.out.println(">>>>>>>"+o.getOutput().replaceAll("\r", " ").replaceAll("\n", " "));
		o = driver.getNextOutput();
		System.out.println(">>>>>>>"+o.getOutput().replaceAll("\r", " ").replaceAll("\n", " "));
		o = driver.getNextOutput();
		System.out.println(">>>>>>>"+o.getOutput().replaceAll("\r", " ").replaceAll("\n", " "));

		driver.setNextInput("1");
		o = driver.getNextOutput();
		System.out.println(">>>>>>>"+o.getOutput().replaceAll("\r", " ").replaceAll("\n", " "));
		o = driver.getNextOutput();
		System.out.println(">>>>>>>"+o.getOutput().replaceAll("\r", " ").replaceAll("\n", " "));
		o = driver.getNextOutput();
		System.out.println(">>>>>>>"+o.getOutput().replaceAll("\r", " ").replaceAll("\n", " "));
		o = driver.getNextOutput();
		System.out.println(">>>>>>>"+o.getOutput().replaceAll("\r", " ").replaceAll("\n", " "));
		o = driver.getNextOutput();
		System.out.println(">>>>>>>"+o.getOutput().replaceAll("\r", " ").replaceAll("\n", " "));
	}
	
	@After
	public void waitFor() {
		driver.shutdown();
	}

}
