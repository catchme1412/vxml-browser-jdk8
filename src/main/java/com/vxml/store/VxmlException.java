package com.vxml.store;

import java.net.URISyntaxException;

import javax.script.ScriptException;

public class VxmlException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public VxmlException(URISyntaxException e) {
        super(e);
    }

    public VxmlException(ScriptException e) {
        super(e);
    }

}
