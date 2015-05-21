package com.vxml.core;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

import com.vxml.tag.AbstractTag;
import com.vxml.tag.Tag;
import com.vxml.tag.TagFactory;
import com.vxml.utils.XmlUtils;

public class VxmlParser {

    public void walk(Node node) {
//        System.out.println("WALK..." + node);
        if (XmlUtils.isEmptyOrComment(node)) {
            return;
        }
        Tag tag = TagFactory.get(node);
//        System.out.println("START:" + node.getNodeType() + "::" + tag);
        // // stack.add(tag);
        tag.startTag();
        ((AbstractTag) tag).tryExecute();
        
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            walk(child);
        }

        // System.out.println("END:" + tag);
        tag.endTag();
    }

    public void parse(VxmlDoc vxmlDoc) {
        Document doc = vxmlDoc.getXmlDoc();
        NodeIterator ni = ((DocumentTraversal) doc).createNodeIterator(doc.getDocumentElement(), NodeFilter.SHOW_ALL
                & ~NodeFilter.SHOW_COMMENT, null, true);
        walk(ni.nextNode());
    }

}
