package com.vxml.tag;

import com.vxml.core.VxmlBrowser;
import com.vxml.core.VxmlDoc;

public class GotoTag extends AbstractTag {
    private String src;
    private String target;
    private String next;
    private String expr;

    @Override
    public void startTag() {
        target = src != null ? src : next;
        String executeScript = null;
        if (expr != null) {
            executeScript = (String) VxmlBrowser.getVxmlExecutionContext().executeScript(expr);
            target = executeScript;
        } else {
            target = next;
        }

    }

    @Override
    public void execute() {
        // if (target.startsWith("#")) {
        // Tag form =
        // VxmlBrowser.getVxmlExecutionContext().getTag(target.substring(1));
        // if (form == null) {
        // NodeList forms =
        // getNode().getOwnerDocument().getElementsByTagName("form");
        // for (int i = 0; i < forms.getLength(); i++) {
        // String formName =
        // forms.item(i).getAttributes().getNamedItem("id").getNodeValue();
        // if (formName.equals(target.substring(1))) {
        // Node formNode = forms.item(i);
        // form = TagFactory.get(formNode);
        // }
        // }
        // }
        // ((AbstractTag) form).tryExecute();
        // } else {
        new VxmlDoc(target).play();
        // }
    }

}
