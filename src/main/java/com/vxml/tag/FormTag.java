package com.vxml.tag;


public class FormTag extends AbstractTag {

	private String id;
	
	@Override
	public void startTag() {
		if (id != null) {
//			VxmlBrowser.getVxmlExecutionContext().storeTag(id, this);
		}
	}

}
