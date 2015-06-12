package com.vxml.tag;

import java.net.URI;
import java.net.URISyntaxException;

import com.vxml.core.VxmlBrowser;
import com.vxml.store.DocumentStore;

public class DataTag extends AbstractTag {

    private String name;
    private String src;
    private String namelist;
    
	@Override
	public void execute() {
		StringBuilder queryParams = prepareUrl();
		StringBuilder result = null;
		try {
			result = new DocumentStore().getData(new URI(queryParams.toString()));
			VxmlBrowser.getVxmlExecutionContext().assignVarWithoutSubdialog(name, result.toString().replaceAll("'", "\\\\'"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

    private StringBuilder prepareUrl() {
        StringBuilder queryParams = new StringBuilder();
		queryParams.append(src);
		queryParams.append("?");
		String nameListArray[] = namelist.split(" ");
		for (int i = 0; i < nameListArray.length; i++) {
			queryParams.append(nameListArray[i]);
			queryParams.append("=");
			queryParams.append(VxmlBrowser.getVxmlExecutionContext().getScriptVar(nameListArray[i]));
			queryParams.append("&");
		}
        return queryParams;
    }

}
