package com.vxml.tag;

import java.util.HashMap;
import java.util.Map;

import com.vxml.core.VxmlDoc;

public class VxmlTag extends AbstractTag {

    private String application;
    
    private static Map<String, String> appRootMap =new HashMap<String, String>();

    @Override
    public void execute() {
        if (application != null && !appRootMap.containsKey(application)) {
            appRootMap.put(application, application);
            new VxmlDoc(application).play();
            System.out.println("APP_ROOT is complete"); 
        }
    }
    
}
