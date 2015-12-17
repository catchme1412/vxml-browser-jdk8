package com.vxml.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Stack;

import com.vxml.store.DocumentStore;

public class VxmlBrowser {

    private static VxmlExecutionContext vxmlExecutionContext;

    private static Stack<VxmlExecutionContext> vxmlExecutionContextStack;

    private String entryPointUrl;
    static {
        vxmlExecutionContextStack = new Stack<>(); 
    }

    public VxmlBrowser() {
        vxmlExecutionContext = new VxmlExecutionContext();
        vxmlExecutionContextStack.add(vxmlExecutionContext);
    }

    public static void pushNewExecutionContextForSubdialog() {
        vxmlExecutionContextStack.add(new VxmlExecutionContext());
    }
    
    public static void clearTopExecutionContextForSubdialog() {
        vxmlExecutionContextStack.pop();
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
        vxmlBrowser.setEntryPointUrl("http://localhost:8585/newCallController.htm?dnis=8008247019&ani=19733689400&uuid=5FB69FD283A411E58F19442B03ABD780&newCallSuccess=true");
        vxmlBrowser.start();
    }

    public static VxmlExecutionContext getVxmlExecutionContext() {
        return vxmlExecutionContextStack.peek();
    }

}
