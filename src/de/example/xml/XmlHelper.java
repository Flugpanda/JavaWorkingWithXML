package de.example.xml;

import java.io.File;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class XmlHelper {
	/**
	 * Check to .xml file extension of a given file
	 * 
	 * @param xmlFile	The file that acts to be an xml file
	 * @return			true if file ends with .xml, false if not
	 */
	public static boolean hasXmlFileExtension(File xmlFile) {
		// Make sure it has the correct extension
		String filename = xmlFile.getName();
		String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		extension = extension.toLowerCase();
		if (extension.contains("xml")) {
			return true;
		}
		
		System.err.println("XmlHelper" + ".hasXmlFileExtension" + ": Files does ot end with .xml");
		System.err.println("\t file: " + xmlFile.getAbsolutePath());
		return false;
	}
}
