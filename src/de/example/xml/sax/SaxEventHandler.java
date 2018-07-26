package de.example.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class SaxEventHandler extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {
		System.out.println("--------------------------> Document Start");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("<-------------------------- Document End");
	}

	/**
	 * This method gets fired, if the parser walks through the start of an element inside the xml
	 * 
	 * @param uri - The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
	 * @param localName - The local name (without prefix), or the empty string if Namespace processing is not being performed.
	 * @param qName - The qualified name (with prefix), or the empty string if qualified names are not available.
	 * @param attributes - The attributes attached to the element. If there are no attributes, it shall be an empty Attributes object.
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		String eName = ("".equals(localName)) ? qName : localName;
//		String eName;
//		if ("".equals(localName)) {
//			eName = qName;
//		} else {
//			eName = localName;
//		}
				
		System.out.println("<" + eName + ">"); // element name
		
		// check for attributes
		if (attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String aName = attributes.getLocalName(i); // Attr name
				if ("".equals(aName))
					aName = attributes.getQName(i);
				System.out.println("\t" + aName + " = " + attributes.getValue(i));
			}
		}
	}

	/**
	 * This method gets fired, if the parser finished walking through an element inside the xml
	 * 
	 * uri - The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.
	 * localName - The local name (without prefix), or the empty string if Namespace processing is not being performed.
	 * qName - The qualified name (with prefix), or the empty string if qualified names are not available.
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		String eName = ("".equals(localName)) ? qName : localName;
		System.out.println("</" + eName + ">");
	}
	
	/**
	 * ch - The characters buffer
	 * start - The start position in the character array.
	 * length - The number of characters to use from the character array.
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String elementContent = new String(ch, start, length );
		System.out.println("\t" + elementContent.trim());
	}

}
