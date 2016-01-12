package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class FieldTag extends AbstractTag {

    private String name;

    @Override
    public void execute() {
    	getVxmlExecutor().executeScript("var " + name);

    }
}
