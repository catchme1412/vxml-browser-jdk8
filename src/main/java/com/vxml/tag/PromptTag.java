package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class PromptTag extends AbstractTag {

    private String cond;

    @Override
    public void startTag() {
//        if (!isSkipExecutePeek() && isProceed()) {
//            isSkipExecute(false);
//        } else {
//            isSkipExecute(true);
//        }
    }

    @Override
    public void endTag() {
//        clearTopSkipExecuteFlag();
    }
    
//    public boolean isProceed() {
////        Boolean isProceed = true;
////        if (cond != null) {
////            isProceed = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
////        }
////        return isProceed;
//    }

}
