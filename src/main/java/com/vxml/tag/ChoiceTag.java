package com.vxml.tag;

import com.vxml.core.VxmlBrowser;
import com.vxml.core.VxmlDoc;
import com.vxml.core.input.VxmlNoInputEvent;

public class ChoiceTag extends AbstractTag {

    private String dtmf;
    private String expr;

    @Override
    public void execute() {
        String userInput = null;
        try {
            userInput = VxmlBrowser.getVxmlExecutionContext().ioHandler.readInput();
        } catch (VxmlNoInputEvent e) {
            e.printStackTrace();
        }
        if (userInput != null && dtmf.equals(userInput)) {
            System.out.println("USER CHOICE....." + userInput);
            String url = (String) VxmlBrowser.getVxmlExecutionContext().executeScript(expr);
            //TODO May lead to OutofMemory or StackOverFlow as the remaining tags in
            // this file may not be executed and may never be executed
            new VxmlDoc(url).play();
        } else {
            VxmlBrowser.getVxmlExecutionContext().requestDtmfInput();
        }
    }
}
