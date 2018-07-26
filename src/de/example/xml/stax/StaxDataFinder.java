package de.example.xml.stax;

import java.io.File;
import javax.xml.stream.*;
import javax.xml.transform.stream.StreamSource;

/**
 * This class uses a stax parser to work with xml files
 * 
 * @author Bastian Bräunel
 *
 */
public class StaxDataFinder {

	/**
	 * Returns the content of all the specific elements inside a xml
	 * 
	 * @param file    the xml file
	 * @param element the element you are looking for
	 * @return a String with the content
	 */
	public String findData(File file, String element) {
		StringBuilder builder = new StringBuilder();

		// get an instance of the facctoy
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			// get the stream reader
			XMLStreamReader stax = inputFactory.createXMLStreamReader(new StreamSource(file));
			boolean elementFound = false;
			
			// loop over the xml file
			while (stax.hasNext()) {
				// get next element
				stax.next();
				
				// check the current content 
				String name = (stax.hasName()) ? stax.getName().getLocalPart().trim() : null;
				String text = (stax.hasText()) ? stax.getText().trim() : null;
				
				// dealing with the content
				// found a element
				if (name != null) { 		
					// set flag to signal the the next text will the one you are looking for
					elementFound = name.equals(element) ? true : false;
				} 
				// found the content of the element
				else if (text != null && 				// content is available
						text.compareTo("") != 0 && 		// content is not empty
						elementFound) { 				// flag is set
					// append the newly found content
					builder.append(element + ": " + text + "\n");
					// reset the flag
					elementFound = false;
				} else {
					continue;
				}
			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return builder.toString();
	}
}
