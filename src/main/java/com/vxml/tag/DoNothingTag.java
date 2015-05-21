package com.vxml.tag;

import org.w3c.dom.Node;

public class DoNothingTag extends AbstractTag {

    
	public DoNothingTag(Node node) {
        setNode(node);
    }

    @Override
	public void execute() {

	}

}
