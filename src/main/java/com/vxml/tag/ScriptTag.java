package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class ScriptTag extends AbstractTag {

    private String src;
    

    @Override
    public void execute() {
        if (src != null) {
            VxmlBrowser.getVxmlExecutionContext().executeScriptFile(src);
        } else {
            VxmlBrowser.getVxmlExecutionContext().executeScript(getNode().getTextContent());
        }
    }
}
