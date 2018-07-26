package de.example.xml;

import java.io.File;

import de.example.xml.sax.SaxParser;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class Main {

	public static void main(String[] args) {
		File xmlFile = new File("./res/employee.xml");
		SaxParser saxParser = new SaxParser();
		saxParser.parseXML(xmlFile);
		
	}

}
