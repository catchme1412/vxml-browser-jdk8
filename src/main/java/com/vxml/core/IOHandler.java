package com.vxml.core;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.vxml.core.input.ConsoleInput;
import com.vxml.core.input.ProcessTrigger;
import com.vxml.core.input.VxmlNoInputEvent;

public class IOHandler {

    private Queue<String> dtmfInputQueue;

    private Queue<OutputWrapper> outputQueue;

    public Queue<OutputWrapper> getOutputQueue() {
        return outputQueue;
    }

    public void setOutputQueue(Queue<OutputWrapper> outputQueue) {
        this.outputQueue = outputQueue;
    }

    public void recordOutput(OutputType type, String output) {
        if (this.outputQueue == null) {
            this.outputQueue = new LinkedBlockingQueue<OutputWrapper>();
        }
        this.outputQueue.add(new OutputWrapper(type, output));
        switch (type) {
        case AUDIO:
            play(output);
            break;
        case TTS:
            tts(output);
        }
    }

    private void tts(String text) {
        String[] cmd = { "/bin/sh", "-c", "echo '" + text + "' | festival --tts" };
        try {
            new ProcessTrigger().trigger(cmd);
        } catch (IOException | InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void play(String output) {
        String[] cmd = { "/bin/sh", "-c", "wget " + output + " -O /tmp/ivr.wav" };
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            // wait for the file to download
            p.waitFor();
            String[] cmdWav = { "/bin/sh", "-c", "play /tmp/ivr.wav" };
            new ProcessTrigger().trigger(cmdWav);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String nextOutput() {
        if (outputQueue.isEmpty()) {
            while (outputQueue.isEmpty()) {
                try {
                    Thread.sleep(1000);
                    // System.out.println("waiting for output");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return outputQueue.remove().getOutput();
    }

    public Queue<String> getDtmfInputQueue() {
        return dtmfInputQueue;
    }

    public void setDtmfInputQueue(Queue<String> dtmfInputQueue) {
        this.dtmfInputQueue = dtmfInputQueue;
    }

    public String readInput() throws VxmlNoInputEvent {
        String input = null;
        int i = 0;
        System.err.println("INPUT>");
        while (dtmfInputQueue.isEmpty() && i++ < 50) {
            try {
                // give some time for the input thread to provide data.
                Thread.sleep(1000);
                // System.out.println("waiting for input");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (!dtmfInputQueue.isEmpty()) {
            input = dtmfInputQueue.remove();
        } else {
            input = new ConsoleInput(5, TimeUnit.SECONDS).readLine();
        }
        System.out.println("INPUT:" + input);
        return input;
    }
}
