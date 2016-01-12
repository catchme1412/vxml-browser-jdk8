package com.vxml.core.input;

import java.util.concurrent.TimeoutException;

public class NoInputEvent extends Exception {

    public NoInputEvent(TimeoutException e) {
    }

}
