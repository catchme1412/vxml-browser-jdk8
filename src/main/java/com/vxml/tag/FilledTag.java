package com.vxml.tag;

import com.vxml.core.VxmlBrowser;
import com.vxml.core.VxmlExecutionContext;
import com.vxml.core.input.VxmlNoInputEvent;

public class FilledTag extends AbstractTag {

    @Override
    public void execute() {
        try {
            System.out.println("In Filled" + Thread.currentThread().getId());
            String input = VxmlExecutionContext.ioHandler.readInput();
            System.out.println("READ:" + input);
            String fieldName = getFieldName();
            System.err.println("var " + fieldName + " = " + input);
            VxmlBrowser.getVxmlExecutionContext().assignVar(fieldName, input);
        } catch (VxmlNoInputEvent e) {
            System.out.println("NO INPUT : " + e);
        }
    }
    
    
    private String getFieldName() {
        return getNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
    }

}
