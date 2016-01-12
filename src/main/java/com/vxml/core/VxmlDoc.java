package com.vxml.core;

import org.w3c.dom.Document;

import com.vxml.store.DocumentStore;

public class VxmlDoc {

//    private String documentUrl;
    private Document xmlDoc;
    private DocumentStore documentStore;

    public VxmlDoc(String documentUrl) {
//        this.documentUrl = documentUrl;
        documentStore = new DocumentStore();
        xmlDoc = documentStore.getDoc(documentUrl);
    }

    public void play(VxmlExecutor vxmlExecutor) {
        VxmlParser vxmlParser = new VxmlParser();
        vxmlParser.parse(this, vxmlExecutor);
    }

    public Document getXmlDoc() {
        return xmlDoc;
    }

}
