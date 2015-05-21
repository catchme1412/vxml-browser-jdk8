package com.vxml.tag;

import org.w3c.dom.Node;

public interface Tag {

    public void startTag();

    public void execute();

    public void endTag();

    public void setNode(Node node);
}
