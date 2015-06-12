package com.vxml.tag;

import java.util.List;

import jdk.nashorn.internal.objects.NativeArray;

import com.vxml.core.VxmlBrowser;

public class ForeachTag extends AbstractTag {

    private String item;
    private String array;

    @Override
    public void startTag() {
        VxmlBrowser.getVxmlExecutionContext().executeScript("var " + item);
        VxmlBrowser.getVxmlExecutionContext().executeScript("var " + array);
        isExecute(isExecutePeek());
    }

    @Override
    public void execute() {
        Object arr = VxmlBrowser.getVxmlExecutionContext().executeScript(array);
        if (arr instanceof List) {
            for (Object o : (List) arr) {
                VxmlBrowser.getVxmlExecutionContext().assignVar(item, o);
                executeChildTree(getNode());
            }
        } else if (arr instanceof NativeArray) {
            NativeArray ary = (NativeArray) arr;
            Object[] a = new Object[(int) ary.getLength()];
//            for (Object o : ary.getIds()) {
//                int index = (Integer) o;
//                a[index] = ary.get(index, null);
//                Object val = a[index];
//
//                VxmlBrowser.getVxmlExecutionContext().assignVar(item, val);
//                System.out.println("LOOOP:" + o);
//                executeChildTree(getNode());
//            }
        }
        if (array instanceof String) {
            Object val = VxmlBrowser.getVxmlExecutionContext().executeScript(array.toString());
        }
    }

    @Override
    public void endTag() {
        clearTopExecuteFlag();
    }

}
