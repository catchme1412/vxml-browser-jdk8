package com.vxml.tag;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.core.VxmlBrowser;
import com.vxml.core.VxmlDoc;

public class SubdialogTag extends AbstractTag {

    private String src;
    private String name;
    private String srcexpr;
    
    private Map<String, Object> paramBackup;

    @Override
    public void startTag() {
        getVxmlExecutor().executeScript("var " + name + " = {}");
        paramBackup = new HashMap<>();
    }

    @Override
    public void execute() {
        src = src != null ? src : (String) getVxmlExecutor().getScriptVar(srcexpr);
        backupParamValues();
        pushNewExecutionContextForSubdialog();
        createParams();
//        VxmlExecutionContext.markSubdialog(name);
        new VxmlDoc(src).play(getVxmlExecutor());
    }

    

	private void backupParamValues() {
        NodeList paramList = getNode().getChildNodes();
        for (int i = 0; i < paramList.getLength(); i++) {
            Node node = paramList.item(i);
            boolean isParamTag = "param".equals(node.getNodeName());
            if (isParamTag) {
                String name = node.getAttributes().getNamedItem("name").getNodeValue();
                String expr = node.getAttributes().getNamedItem("expr").getNodeValue();
                paramBackup.put(name, getVxmlExecutor().getScriptVar(expr));
            }
        }
        
    }

    @Override
    public void endTag() {
//        VxmlExecutionContext.clearTopSubdialogFlag();
        VxmlBrowser.clearTopExecutionContextForSubdialog();
    }
    //TODO just loop on paramBackup and set the values;
    private void createParams() {
        NodeList paramList = getNode().getChildNodes();
        for (int i = 0; i < paramList.getLength(); i++) {
            Node node = paramList.item(i);
            boolean isParamTag = "param".equals(node.getNodeName());
            if (isParamTag) {
                String name = node.getAttributes().getNamedItem("name").getNodeValue();
                String expr = node.getAttributes().getNamedItem("expr").getNodeValue();
                createParamEntry(name, (paramBackup.get(expr)));
            }
        }
    }

    private void createParamEntry(String param, Object val) {
        getVxmlExecutor().assignVar(param, val);
        String va = (String) getVxmlExecutor().getScriptVar("paramPassed");
    }
}
