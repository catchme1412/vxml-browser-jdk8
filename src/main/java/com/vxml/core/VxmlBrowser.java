package com.vxml.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Stack;

import com.vxml.store.DocumentStore;

public class VxmlBrowser {

    private String entryPointUrl;
    private VxmlExecutor vxmlExecutor;

    public VxmlBrowser(VxmlExecutor vxmlExecutor) {
        this.vxmlExecutor = vxmlExecutor;
    }

    public static void pushNewExecutionContextForSubdialog() {
    	
    }
    
    public static void clearTopExecutionContextForSubdialog() {
    }

    public void setEntryPointUrl(String entryPointUrl) {
        this.entryPointUrl = entryPointUrl;
        URI uri = null;
        try {
            uri = new URI(entryPointUrl);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DocumentStore.setDocBaseUrl(uri.getScheme() + "://" + uri.getAuthority());
    }

    public void start() {
        VxmlDoc vxmlDoc = new VxmlDoc(entryPointUrl);
        
        vxmlDoc.play(vxmlExecutor);
    }

    public static void main(String args[]) throws Exception {
//        VxmlBrowser vxmlBrowser = new VxmlBrowser();
//        vxmlBrowser.setEntryPointUrl("http://localho?st:8585/ivr/testing/development.vxml");
//        vxmlBrowser.start();
    }


}
