package com.vxml.tag;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.core.VxmlBrowser;
import com.vxml.core.VxmlDoc;
import com.vxml.core.VxmlExecutionContext;

public class SubdialogTag extends AbstractTag {

    private String src;
    private String name;
    private String srcexpr;

    @Override
    public void startTag() {
        VxmlBrowser.getVxmlExecutionContext().executeScript("var " + name + " = {}");
    }

    @Override
    public void execute() {
        src = src != null ? src : (String) VxmlBrowser.getVxmlExecutionContext().getScriptVar(srcexpr);
        VxmlExecutionContext.markSubdialog(name);
        createParams();
        new VxmlDoc(src).play();
    }

    @Override
    public void endTag() {
        VxmlExecutionContext.clearTopSubdialogFlag();
    }

    private void createParams() {
        NodeList paramList = getNode().getChildNodes();
        for (int i = 0; i < paramList.getLength(); i++) {
            Node node = paramList.item(i);
            boolean isParamTag = "param".equals(node.getNodeName());
            if (isParamTag) {
                String name = node.getAttributes().getNamedItem("name").getNodeValue();
                String expr = node.getAttributes().getNamedItem("expr").getNodeValue();
                createParamEntry(name, expr);
            }
        }
    }

    private void createParamEntry(String param, Object val) {
        VxmlBrowser.getVxmlExecutionContext().assignVar(param, val);
        String va = (String) VxmlBrowser.getVxmlExecutionContext().getScriptVar("paramPassed");
        System.out.println(va);
    }
}
