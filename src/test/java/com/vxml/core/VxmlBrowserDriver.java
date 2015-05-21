package com.vxml.core;

import java.net.URISyntaxException;

import com.vxml.tag.AbstractTag;

public class VxmlBrowserDriver {

    private VxmlBrowser vxmlBrowser;
    Thread thread = null;
    
    public void setBrowser(VxmlBrowser vxmlBrowser) {
        this.vxmlBrowser = vxmlBrowser;
    }

    public void start() {
        thread = new Thread(new VxmlBrowserWrapper());
        thread.start();
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.execute(new VxmlBrowserWrapper());
//        executorService.shutdown();
    }
    
    public void waitForCompletion() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

    public String nextOuput() {
        return VxmlExecutionContext.ioHandler.nextOutput();
    }

    public void setEntryPointUrl(String entryPointUrl) throws URISyntaxException {
        vxmlBrowser.setEntryPointUrl(entryPointUrl);
    }

    public void nextInput(String string) {
        VxmlExecutionContext.ioHandler.getDtmfInputQueue().add(string);
    }

    class VxmlBrowserWrapper implements Runnable {

        @Override
        public void run() {
            System.out.println("Started::::::::::::" + vxmlBrowser);
            vxmlBrowser.start();
            System.out.println("End::::::::::::" + vxmlBrowser);
        }
    }

}
