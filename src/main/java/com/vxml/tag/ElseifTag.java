package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class ElseifTag extends LogicalTag {

	private String cond;

	@Override
	public void startTag() {
		Boolean isLogicalBlockExecuted = isLogicalBlockExecuted();
		Boolean isElseIfCondtionTrue = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
		if (!isLogicalBlockExecuted) {
			if (isElseIfCondtionTrue) {
				toggleLogicalBlockStatus(true);
			}
		} else if (isLogicalBlockExecuted) {
			toggleLogicalBlockStatus(false);
		}
	}
}
