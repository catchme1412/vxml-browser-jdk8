package com.vxml.tag;

public class GrammarTag extends AbstractTag {

    @Override
    public void startTag() {
        isSkipExecute(true);
    }
    
    @Override
    public void endTag() {
        clearTopSkipExecuteFlag();
    }
    
}
