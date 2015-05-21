package com.vxml.core;

import java.net.URI;
import java.net.URISyntaxException;

import com.vxml.store.DocumentStore;

public class VxmlBrowser {

    private static VxmlExecutionContext vxmlExecutionContext;

    private String entryPointUrl;
    
    public VxmlBrowser() {
        vxmlExecutionContext = new VxmlExecutionContext();
    }


    public void setEntryPointUrl(String entryPointUrl) throws URISyntaxException {
        this.entryPointUrl = entryPointUrl;
        URI uri = new URI(entryPointUrl);
        DocumentStore.setDocBaseUrl(uri.getScheme() + "://" + uri.getAuthority());
    }

    public void start() {
        VxmlDoc vxmlDoc = new VxmlDoc(entryPointUrl);
        vxmlDoc.play();
    }

    public static void main(String args[]) throws Exception {
        VxmlBrowser vxmlBrowser = new VxmlBrowser();
        vxmlBrowser.setEntryPointUrl("http://localhost:8585/ivr/testing/sao.vxml");
        vxmlBrowser.start();
    }


    public static VxmlExecutionContext getVxmlExecutionContext() {
        return vxmlExecutionContext;
    }
}
