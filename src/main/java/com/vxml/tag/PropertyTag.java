package com.vxml.tag;


public class PropertyTag extends AbstractTag {

    private String name;
    private String value;

    @Override
    public void startTag() {
//        VxmlBrowser.getVxmlExecutionContext().assignVar(name, null);
    }

    @Override
    public void execute() {
        if ("bargein".equals(name)) {
            toggleBargein(Boolean.getBoolean(value));
        }
    }

}
