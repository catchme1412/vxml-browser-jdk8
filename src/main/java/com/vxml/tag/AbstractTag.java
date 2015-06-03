package com.vxml.tag;

import java.util.Stack;

import org.w3c.dom.Node;

import com.vxml.core.VxmlBrowser;
import com.vxml.core.VxmlScriptEngine;
import com.vxml.utils.XmlUtils;

public abstract class AbstractTag implements Tag {

    private Node node;
    private static Stack<Boolean> isExecuteTagStack;
    private static Stack<String> isSubdialogStack;

    static {
        isExecuteTagStack = new Stack<Boolean>();
        isExecuteTagStack.push(true);

        isSubdialogStack = new Stack<String>();
        isSubdialogStack.push("test");
    }

    @Override
    public void startTag() {
        //NOP
    }

    @Override
    public void execute() {
        // System.out.println("AbstractTag.execute..." + node);
    }

    @Override
    public void endTag() {
        //NOP
    }

    public void tryExecute() {
        if (((AbstractTag) this).isExecutePeek()) {
            System.out.println("EXECUTING:" + this + " | " + getDocumentURI() + " | subdialog:" + isSubdialogPeek());
            execute();
        } else {
            // System.out.println("SKIPPING:" + this);
        }
    }

    public String getDocumentURI() {
        return getNode().getOwnerDocument().getDocumentURI();
    }

    @Override
    public String toString() {
        String xml = nodeToString();
        String tag = xml.substring(0, xml.indexOf(">") + 1);
        if (tag.isEmpty()) {
            return "\t#text:" + getNode().getTextContent().trim();
        }
        return tag;
    }

    public String nodeToString() {
        return XmlUtils.nodeToString(getNode());
    }

    public Node getNode() {
        return node;
    }

    public String getParentTag() {
        return node.getParentNode().getNodeName();
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void toggleExecute(Boolean isTrue) {
        Boolean r = isExecuteTagStack.pop();
        isExecuteTagStack.push(isTrue);
    }

    public void isExecute(boolean isSkip) {
        isExecuteTagStack.push(isSkip);
    }

    public static void markSubdialog(String subdialogName) {
        isSubdialogStack.push(subdialogName);
        VxmlBrowser.getVxmlExecutionContext().assignVar(VxmlScriptEngine.getSubdialogNameKey(), "'" + subdialogName + "'");
    }

    public void clearTopExecuteFlag() {
        if (!isExecuteTagStack.isEmpty()) {
            isExecuteTagStack.pop();
        }
    }

    public void clearTopSubdialogFlag() {
        if (!isSubdialogStack.isEmpty()) {
            isSubdialogStack.pop();
            VxmlBrowser.getVxmlExecutionContext().assignVar(VxmlScriptEngine.getSubdialogNameKey(), isSubdialogStack.peek());
        }
    }


    public boolean isExecutePeek() {
        return isExecuteTagStack.peek();
    }

    public boolean isInsideSubdialog() {
        return isSubdialogPeek() != null;
    }

    public String isSubdialogPeek() {
        return isSubdialogStack.peek();
    }

    // similar to walk
    public void executeChildTree(Node startNode) {
        if (XmlUtils.isEmptyOrComment(startNode)) {
            return;
        }

        // recurse
        for (Node child = startNode.getFirstChild(); child != null; child = child.getNextSibling()) {
            AbstractTag tag = (AbstractTag) TagFactory.get(child);
            if (XmlUtils.isEmptyOrComment(child)) {
                continue;
            }
            // System.out.println("START:" + node.getNodeType() + "::" + tag);
            // stack.add(tag);
            tag.startTag();
            // boolean skipBkp = isSkipExecute;
            ((AbstractTag) tag).tryExecute();
            executeChildTree(child);
            tag.endTag();
            // isSkipExecute = skipBkp;
        }

        // System.out.println("END:" + tag);
    }

}
