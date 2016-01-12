package com.vxml.core;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.vxml.core.input.NoInputEvent;

public class VxmlExecutor implements Runnable {

	private VxmlBrowser vxmlBrowser;

	private VxmlScriptEngine vxmlScriptEngine;

	private BlockingQueue<Output> outputQueue;
	private BlockingQueue<Input> inputQueue;

	public VxmlExecutor() {
		vxmlBrowser = new VxmlBrowser(this);
		outputQueue = new LinkedBlockingQueue<Output>();
		inputQueue = new LinkedBlockingQueue<>();
		vxmlScriptEngine = new VxmlScriptEngine();
	}

	public void setEntryPointUrl(String entryPointUrl) {
		vxmlBrowser.setEntryPointUrl(entryPointUrl);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Running");
		vxmlBrowser.start();
	}

	public BlockingQueue<Output> getOutputQueue() {
		return outputQueue;
	}

	public void executeScriptFile(String src) {
		vxmlScriptEngine.executeScriptFile(src);

	}

	public void setOutputQueue(BlockingQueue<Output> outputQueue) {
		this.outputQueue = outputQueue;
	}

	public Object executeScript(String script) {
		return vxmlScriptEngine.eval(script);
	}

	public void assignVar(String var, Object val) {
		vxmlScriptEngine.assignScriptVar(var, val);
	}

	public void assignVarWithoutSubdialog(String var, Object val) {
		vxmlScriptEngine.assignScriptVar(var, val);
	}

	public Object getScriptVar(String var) {

		return vxmlScriptEngine.getScriptVar(var);
	}

	public String getScriptVars() {
		return vxmlScriptEngine.scopeVars();
	}

	public void recordOutput(OutputType tts, String text, boolean bargeinPeek) {
		Output e = new Output();
		e.setOutputType(tts);
		e.setOutput(text);
		outputQueue.add(e);
	}

	public Input readInput() throws NoInputEvent, InterruptedException {
		while(inputQueue.isEmpty()) {
			
		}
		return inputQueue.poll(5, TimeUnit.SECONDS);
	}

	public void requestDtmfInput() {
		// TODO Auto-generated method stub

	}

	public void setNextInput(String string) {
		if (string == null) {
			throw new RuntimeException("Noooooo");
		}
		Input e = new Input();
		e.setInputChar(string);
		inputQueue.add(e);

	}

}
