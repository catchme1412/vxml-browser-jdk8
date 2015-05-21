package com.vxml.tag;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.w3c.dom.Node;

public class TagFactory {

	public static Tag get(Node node) {
		String tagName = getTagName(node);
		try {
			Constructor c = Class.forName(tagName).getConstructor();
			Tag tag = (Tag) c.newInstance();
			tag.setNode(node);
			setFieldValues(node, tag);
			return tag;
		} catch (Exception e) {
			System.err.println("NO CLASS:" + e.getMessage());
			return new DoNothingTag(node);
		}
	}

    private static void setFieldValues(Node node, Tag tag) throws IllegalAccessException {
        Field[] fields = tag.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Node value = node.getAttributes().getNamedItem(field.getName());
            if (value != null) {
                field.set(tag, value.getNodeValue());
            }
        }
    }

	private static String getTagName(Node node) {
		String nodeName = node.getNodeName();
		if (nodeName.equals("cisco-data")) {
		    nodeName = "data";
		}
		if (nodeName.startsWith("#")) {
			nodeName = nodeName.substring(1);
		}
		if (node.getNodeType() == Node.CDATA_SECTION_NODE) {
		    nodeName = "CDATASection";
		}
		if (nodeName.contains("-")) {
		    nodeName = nodeName.replaceAll("-", "");
		}
		return "com.vxml.tag." + Character.toUpperCase(nodeName.charAt(0)) + nodeName.substring(1) + "Tag";
	}

}
