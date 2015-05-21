package com.vxml.core.input;

import java.util.concurrent.TimeoutException;

public class VxmlNoInputEvent extends Exception {

    public VxmlNoInputEvent(TimeoutException e) {
    }

}
