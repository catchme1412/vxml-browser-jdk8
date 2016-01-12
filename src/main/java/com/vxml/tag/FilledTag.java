package com.vxml.tag;

import com.vxml.core.Input;
import com.vxml.core.input.NoInputEvent;

public class FilledTag extends AbstractTag {

    @Override
    public void execute() {
        try {
//            System.out.println("In Filled" + Thread.currentThread().getId());
            Input input = getVxmlExecutor().readInput();
            System.out.println("READ:" + input.getInputChar());
            String fieldName = getFieldName();
            System.err.println("var " + fieldName + " = " + input.getInputChar());
            getVxmlExecutor().assignVar(fieldName, input.getInputChar());
        } catch (NoInputEvent | InterruptedException e) {
            System.out.println("NO INPUT : " + e);
        }
    }
    
    
    private String getFieldName() {
        return getNode().getParentNode().getAttributes().getNamedItem("name").getNodeValue();
    }

}
