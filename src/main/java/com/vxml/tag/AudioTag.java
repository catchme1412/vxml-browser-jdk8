package com.vxml.tag;

import com.vxml.core.OutputType;

public class AudioTag extends AbstractTag {

    private String src;
    private String expr;
    
    @Override
    public void execute() {
        String audioFile = src != null ? src : (String) getVxmlExecutor().executeScript(expr);
//        try {
//        	String[] cmd = { "/bin/sh", "-c", "wget " + audioFile + " -O /tmp/ivr.wav" };
//			Process p = Runtime.getRuntime().exec(cmd);
//			p.waitFor();
//			String[] cmdWav = { "/bin/sh", "-c", "play /tmp/ivr.wav" };
//			Process pa = Runtime.getRuntime().exec(cmdWav);
//			pa.waitFor();
//		} catch (IOException | InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        getVxmlExecutor().recordOutput(OutputType.AUDIO, audioFile, false);
//        if (audioFile != null) {
//            VxmlExecutionContext.ioHandler.recordOutput(OutputType.AUDIO, audioFile, isBargeinPeek());
//        }
    }
    
}
