package com.vxml.tag;


public class BreakTag extends AbstractTag {


    @Override
    public void execute() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
