import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.vxml.core.Output;
import com.vxml.core.VxmlExecutor;

public class VxmlExecutionDriver {
	private ExecutorService executor;
	private String entryPointUrl;
	private VxmlExecutor vxmlExecutor;

	public void start() {
		executor = Executors.newFixedThreadPool(1);
		vxmlExecutor = new VxmlExecutor();
		vxmlExecutor.setEntryPointUrl(entryPointUrl);
		executor.execute(vxmlExecutor);
		// try {
		// r.get();
		// } catch (InterruptedException | ExecutionException e) {
		// e.printStackTrace();
		// }
	}

	public void shutdown() {
		executor.shutdown();
	}

	public void setEntryPontUrl(String entryPointUrl) {
		this.entryPointUrl = entryPointUrl;

	}

	public void setNextInput(String string) {
		vxmlExecutor.setNextInput(string);
	}

	public Output getNextOutput() throws InterruptedException {
		
		while(vxmlExecutor.getOutputQueue().isEmpty()) {
//			System.out.println("waiting for output");
		}
		System.out.println("HHHHHHHHHHHHHh");
		return vxmlExecutor.getOutputQueue().take();
	}

	public Output getNextOuput(long timeout) throws InterruptedException {
		
		return getNextOutput();
	}

	public void setNextBreakpoint(String string) {
		
	}
}
