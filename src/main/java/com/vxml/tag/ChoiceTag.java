package com.vxml.tag;

import com.vxml.core.Input;
import com.vxml.core.VxmlDoc;
import com.vxml.core.input.NoInputEvent;

public class ChoiceTag extends AbstractTag {

    private String dtmf;
    private String expr;

    @Override
    public void startTag() {
    	// TODO Auto-generated method stub
    	super.startTag();
    }
    @Override
    public void execute() {
        Input userInput = null;
        try {
            userInput = getVxmlExecutor().readInput();
        } catch (NoInputEvent | InterruptedException e) {
            e.printStackTrace();
        }
        if (userInput != null && dtmf.equals(userInput.getInputChar())) {
            System.out.println("USER CHOICE....." + userInput.getInputChar());
            String url = (String) getVxmlExecutor().executeScript(expr);
            //TODO May lead to OutofMemory or StackOverFlow as the remaining tags in
            // this file may not be executed and may never be executed
            new VxmlDoc(url).play(getVxmlExecutor());
        } else {
            getVxmlExecutor().requestDtmfInput();
        }
    }
}
