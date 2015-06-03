package com.vxml.tag;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vxml.core.VxmlBrowser;
import com.vxml.core.VxmlDoc;

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
        createParams();
        markSubdialog(name);
        new VxmlDoc(src).play();
    }

    @Override
    public void endTag() {
        clearTopSubdialogFlag();
    }

    private void createParams() {
        NodeList paramList = getNode().getChildNodes();
        for (int i = 0; i < paramList.getLength(); i++) {
            Node node = paramList.item(i);
            if ("param".equals(node.getNodeName())) {
                String param = node.getAttributes().getNamedItem("expr").getNodeValue();
                Object val = VxmlBrowser.getVxmlExecutionContext().getScriptVar(param);
                createParamEntry(param, val);
            }
        }
    }

    private void createParamEntry(String param, Object val) {
        VxmlBrowser.getVxmlExecutionContext().assignVar(name + "." + param, val);
    }
}
