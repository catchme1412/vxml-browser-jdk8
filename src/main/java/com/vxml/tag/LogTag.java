package com.vxml.tag;

import java.util.logging.Logger;

public class LogTag extends AbstractTag {

    private static final Logger log = Logger.getLogger(LogTag.class.getName());

    @Override
    public void execute() {
        String message = getNode().getTextContent();
        log.info(message);
    }

}
