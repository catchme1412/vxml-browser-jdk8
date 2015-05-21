package com.vxml.tag;

import com.vxml.core.VxmlDoc;

public class VxmlTag extends AbstractTag {

    private String application;

    @Override
    public void execute() {
        if (application != null) {
            new VxmlDoc(application).play();
        }
    }
    
}
