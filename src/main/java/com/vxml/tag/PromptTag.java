package com.vxml.tag;

import com.vxml.core.Output;


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
    
//    @Override
//    public void execute() {
//        String textContent = getNode().getTextContent();
//        if (!textContent.trim().isEmpty()) {
//            Output e = new Output();
//            e.setOutputType("tts");
//            e.setOutput(textContent.trim());
//            getVxmlExecutor().getOutputQueue().add(e);
//        }
//    }

    @Override
    public void endTag() {
//        clearTopSkipExecuteFlag();
    	System.out.println("Ending prompt tag");
    }
    
//    public boolean isProceed() {
////        Boolean isProceed = true;
////        if (cond != null) {
////            isProceed = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
////        }
////        return isProceed;
//    }

}
