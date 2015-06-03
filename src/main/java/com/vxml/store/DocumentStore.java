package com.vxml.store;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.ClientProtocolException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DocumentStore {

	private static HttpCaller httpCaller;
    private static String docBaseUrl;

	public DocumentStore() {
		if (httpCaller == null) {
			httpCaller = new HttpCaller();
			httpCaller.startSession();
		}
	}

	public Document getDoc(URI uri) {
		// System.out.println("Fetch:" + uri);
		InputStream is = null;
		Document doc = null;
		try {
			is = getInputStream(uri);
			// is = new UrlFetchService().fetchInputStream(uri);
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(false);
			domFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			doc = builder.parse(is);
			doc.setDocumentURI(uri.toString());
		} catch (Exception e) {
			System.err.println("FAILED TO FETCH:" + uri);
			System.exit(-1);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return doc;
	}

	public InputStream getInputStream(URI uri) throws ClientProtocolException, IOException {
		InputStream is;
		String result = httpCaller.doGet(uri.toString());
		is = new ByteArrayInputStream(result.getBytes());
		return is;
	}

	public StringBuilder getData(String relativeUri) {
	    URI uri = getFullUri(relativeUri);
	    return getData(uri);
	}
	public StringBuilder getData(URI uri) {
		StringBuilder builder = new StringBuilder();
		try {
			InputStream in = getInputStream(getFullUri(uri.toString()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder;
	}

	public static Document convertXmlString(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    public Document getDoc(String documentUrl) {
        URI uri = getFullUri(documentUrl);
        return getDoc(uri);
    }
    
    public static URI getFullUri(String uri) {
        URI u = null;
        try {
            u = new URI(uri);
            if (u.getScheme() == null) {
                u = new URI(docBaseUrl + uri);
            }
        } catch (URISyntaxException e) {
            throw new VxmlException(e);
        }
        return u;

    }

    public static String getDocBaseUrl() {
        return docBaseUrl;
    }

    public static void setDocBaseUrl(String docBaseUrl) {
        DocumentStore.docBaseUrl = docBaseUrl;
    }
}
