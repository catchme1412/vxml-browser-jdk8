package com.vxml.core;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

import com.vxml.core.input.ConsoleInput;
import com.vxml.core.input.VxmlNoInputEvent;

public class IOHandler {

    private Queue<String> dtmfInputQueue;

    private Queue<String> outputQueue;

    public IOHandler() {
        System.out.println("FFFF");
    }
    
    public Queue<String> getOutputQueue() {
        return outputQueue;
    }

    public void setOutputQueue(Queue<String> outputQueue) {
        this.outputQueue = outputQueue;
    }

    public void recordOutput(String output) {
        this.outputQueue.add(output);
    }

    public String nextOutput() {
        if (outputQueue.isEmpty()) {
            while(outputQueue.isEmpty()) {
                try {
                    Thread.sleep(1000);
//                    System.out.println("waiting for output");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return outputQueue.remove();
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
        
        while (dtmfInputQueue.isEmpty() && i++ < 50) {
            try {
                //give some time for the input thread to provide data.
                Thread.sleep(1000);
//                System.out.println("waiting for input");
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
