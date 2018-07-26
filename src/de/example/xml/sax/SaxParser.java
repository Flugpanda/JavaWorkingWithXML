package de.example.xml.sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import de.example.xml.XmlHelper;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class SaxParser {
	
	/**
	 * Default constructor
	 */
	public SaxParser() {
	}
	
	/**
	 * Parse xml using the custom event handler {@link SaxEventHandler}
	 * 
	 * @param file	The xml file to pase
	 */
	public void parseXML(File file) {
		
		// check xml extension
		if (!XmlHelper.hasXmlFileExtension(file)) {
			return;
		}
		
		try {
		      // Use an instance of ourselves as the SAX event handler
		      DefaultHandler handler = new SaxEventHandler();
		      // Parse the input with the default (non-validating) parser
		      SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
		      saxParser.parse( file, handler );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
