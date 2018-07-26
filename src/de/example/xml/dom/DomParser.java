package de.example.xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import de.example.xml.XmlHelper;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class DomParser {

	/**
	 * Default Constructor
	 */
	public DomParser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the root element of the xml dom
	 * 
	 * @param file	The xml file that shall be parsed
	 * @return
	 */
	public Element getDomFromFile(File file) {
		
		Document dom = loadXMLFromFile(file);
		
		if (dom != null) {
			return dom.getDocumentElement();
		}
		
		return null;
	}

	/**
	 * Use a xsd file to validate the xml file
	 * 
	 * @param xsdFile	The XSD file
	 * @param xmlFile	The XML file
	 * @return			true if valid false if not
	 */
	public boolean isValid(File xsdFile, File xmlFile) {

		// check if the parameters
		if (xsdFile == null || xmlFile == null) {
			System.err.println(this.getClass().toString() + ".isValid" + ": Files for validation mustn't be a null value.");
			System.err.println("\t xsdFile: " + xsdFile);
			System.err.println("\t xmlFile: " + xmlFile);
			return false;
		}
				
		Schema schema = null;
		Document dom = loadXMLFromFile(xmlFile);
		
		if (dom == null) {
			System.err.println(this.getClass().toString() + ".isValid" + ": Can't get dom from given xml file.");
			System.err.println("\t xmlFile: " + xmlFile.getAbsolutePath());
			return false;
		}
				
		try {
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory factory = SchemaFactory.newInstance(language);
			schema = factory.newSchema(xsdFile);

			Validator validator = schema.newValidator();
			validator.validate(new DOMSource(dom));
			
			return true;
			
		} catch (Exception e) {
			System.err.println(this.getClass().toString() + ".isValid" + ": xml is not valid!");
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * Returns an DOM object from the given file
	 * 
	 * @param xmlFile	the file to parse
	 * @return			null if not successful
	 */
	private Document loadXMLFromFile(File xmlFile) {
		
		// check if the file is an xml file
		if (!XmlHelper.hasXmlFileExtension(xmlFile)) {
			return null;
		}
		
		try {
			//obtain an instance of a factory that can give us a document builder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// get an instance of a builder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// parse the specified file
			Document document = builder.parse(xmlFile);
			// Normalize the XML Structure
			document.getDocumentElement().normalize();
			
			return document;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
