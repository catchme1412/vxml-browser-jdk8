package com.vxml.core.input;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ProcessTrigger {

    public String trigger(String[] cmd, boolean isKillEnabled) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Process p = Runtime.getRuntime().exec(cmd);
        ProcessRunThread task = new ProcessRunThread(p);
        Future<Process> f = executorService.submit(task);
        if (isKillEnabled) {
            ProcessKillThread task2 = new ProcessKillThread(p);
            Future<String> f1 = executorService.submit(task2);
            f.cancel(true);
            return f1.get(5000,  TimeUnit.MILLISECONDS);
        } else {
            p.waitFor();
            return null;
        }
    }
    
    public static void main(String args[]) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        String[] cmdWav = { "/bin/sh", "-c", "play /tmp/posOptions.wav" };
        String p = new ProcessTrigger().trigger(cmdWav, false);
        System.out.println("Your input" + p);
        System.out.println("Your input  " + p);
    }
}
