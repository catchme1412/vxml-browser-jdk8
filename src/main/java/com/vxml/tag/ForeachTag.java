package com.vxml.tag;

import java.util.List;
import java.util.function.BiConsumer;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import jdk.nashorn.internal.objects.NativeArray;

import com.vxml.core.VxmlBrowser;

public class ForeachTag extends AbstractTag {

    private String item;
    private String array;

    @Override
    public void startTag() {
        getVxmlExecutor().executeScript("var " + item);
        getVxmlExecutor().executeScript("var " + array);
        isExecute(isExecutePeek());
    }

    @Override
    public void execute() {
        Object arr = getVxmlExecutor().executeScript(array);
        if (arr instanceof List) {
            for (Object o : (List) arr) {
                getVxmlExecutor().assignVar(item, o);
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
//                getVxmlExecutor().assignVar(item, val);
//                System.out.println("LOOOP:" + o);
//                executeChildTree(getNode());
//            }
        } else if (arr instanceof ScriptObjectMirror) {
        	ScriptObjectMirror ary = (ScriptObjectMirror)arr;
			BiConsumer action = new BiConsumer() {

				@Override
				public void accept(Object t, Object u) {
					// TODO Auto-generated method stub
					getVxmlExecutor().assignVar(item, u);
				}
			};
			ary.forEach(action);
        }
        if (array instanceof String) {
            Object val = getVxmlExecutor().executeScript(array.toString());
        }
    }

    @Override
    public void endTag() {
        clearTopExecuteFlag();
    }

}
