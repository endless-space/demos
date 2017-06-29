package com.endless.demo.jaxp;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * 描述: Jaxp
 * 	1, SAX (Simple API for XML) & DOM (Document Object Model)
 */
public class JaxpTest {
	
	/**
	 * DOM API
	 */
	@Test
	public void testJaxp() throws Exception {
		InputStream inputStream = JaxpTest.class.getClassLoader().getResourceAsStream("User.xml");
		InputSource inputSource = new InputSource(inputStream);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(false);
		
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setEntityResolver(new EntityResolver() {
			@Override
			public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
				LOG.info("{} - {}", publicId, systemId);
				return null;
			}
		});
		
		builder.setErrorHandler(new ErrorHandler() {
			public void warning(SAXParseException exception) throws SAXException {
				LOG.warn("解析XML", exception.getMessage());
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				LOG.error("解析XML", exception.getMessage());
			}

			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				LOG.error("解析XML", exception.getMessage());
			}
		});
		
		Document document = builder.parse(inputSource);
		Element root = document.getDocumentElement();
		NodeList children = root.getChildNodes();
		if (children != null && children.getLength() > 0) {
			for (int i = 0, len = children.getLength(); i < len; i++) {
				Node node = children.item(i);
				LOG.info("{}, {}", node, node.getNodeType());
			}
		}
	}
	
	/**
	 * SPI
	 * 	Service provider interface
	 * 	在META-INF/services/类全限定名文件 下面 接口名=实现类名
	 */
	public void testSPI() {
		
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(JaxpTest.class);
}
