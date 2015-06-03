package com.vxml.tag;

public class NoinputTag extends AbstractTag {

    @Override
    public void startTag() {
      isExecute(false);
    }
    
    @Override
    public void endTag() {
        clearTopExecuteFlag();
    }
    
}
