package com.vxml.tag;

public class GrammarTag extends AbstractTag {

    @Override
    public void startTag() {
        isExecute(false);
    }
    
    @Override
    public void endTag() {
        clearTopExecuteFlag();
    }
    
}
