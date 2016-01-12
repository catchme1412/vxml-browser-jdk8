package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class ScriptTag extends AbstractTag {

    private String src;
    
    @Override
    public void execute() {
        if (src != null) {
           getVxmlExecutor().executeScriptFile(src);
        } else {
           getVxmlExecutor().executeScript(getNode().getTextContent());
        }
    }
}
