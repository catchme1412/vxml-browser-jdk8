package com.vxml.core;

import java.util.Collection;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Driver extends RecursiveAction {

    private boolean done;

    class ConsoleInputReaderTask extends RecursiveTask<Collection<String>> {
        private LinkedBlockingQueue sharedInputQueue;

        ConsoleInputReaderTask(LinkedBlockingQueue sharedInputQueue) {
            this.sharedInputQueue = sharedInputQueue;
        }

        @Override
        protected Collection<String> compute() {
            while (!done) {
//                System.out.println("producing...");
                if (sharedInputQueue.isEmpty()) {
                    System.out.println("INPUTTING DATA");
                    sharedInputQueue.add("1");
                } 
            }
            return null;
        }

    }

    class VxmlPlayer extends RecursiveTask<Collection<String>> {
        private LinkedBlockingQueue sharedInputQueue;
        private LinkedBlockingQueue sharedOutputQueue;
        
        VxmlPlayer(LinkedBlockingQueue input, LinkedBlockingQueue output) {
            this.sharedInputQueue = input;
            this.sharedOutputQueue = output;
        }
        @Override
        protected Collection<String> compute() {
            System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVV..........");
            VxmlBrowser vxmlBrowser = new VxmlBrowser();
            VxmlBrowserDriver driver = new VxmlBrowserDriver();
            try {
                driver.setBrowser(vxmlBrowser);
                driver.setEntryPointUrl("http://localhost:8082/com.vxml.browser/test/ifElseIfElseWithNoGrammar.vxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                VxmlExecutionContext.ioHandler.setDtmfInputQueue(sharedInputQueue);
                VxmlExecutionContext.ioHandler.setOutputQueue(sharedOutputQueue);
                driver.start();
                
            }catch(Throwable t) {
                t.printStackTrace();
            }
            driver.waitForCompletion();
            System.out.println("EXITING..........");
//            driver.waitForCompletion();
            return null;
        }

    }
    
    class OutputTask extends RecursiveTask<Collection<String>> {

        private LinkedBlockingQueue sharedOutputQueue;

        public OutputTask(LinkedBlockingQueue output) {
            this.sharedOutputQueue = output;
        }
        
        @Override
        protected Collection<String> compute() {
            while(!done) {
                if (!sharedOutputQueue.isEmpty()) {
//                    Object t = sharedOutputQueue.remove();
//                    System.out.println("OUT:"+t);
                    System.out.println("FFF");
                }
            }
            return null;
        }
        
    }
    

    @Override
    protected void compute() {
        LinkedBlockingQueue<String> sharedInputQueue = new LinkedBlockingQueue<String>();
        LinkedBlockingQueue<String> sharedOutputQueue = new LinkedBlockingQueue<String>();
        ConsoleInputReaderTask p = new ConsoleInputReaderTask(sharedInputQueue);
        VxmlPlayer c = new VxmlPlayer(sharedInputQueue, sharedOutputQueue);
//        OutputTask o = new OutputTask(sharedOutputQueue);
        
        p.fork();
        c.fork();
//        o.fork();
        
        try {
            p.get();
            c.get();
//            o.get();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public static void main(String args[]) {
        Driver job = new Driver();
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(job);
        job.join();
    }

}
