package de.example.xml;

import java.io.File;

import de.example.xml.sax.SaxParser;
import de.example.xml.stax.StaxDataFinder;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class Main {
	
	public static void main(String[] args) {
		File xmlFile = new File("./res/employee.xml");
		SaxParser saxParser = new SaxParser();
		StaxDataFinder staxDataFinder = new StaxDataFinder();
		
		// test out sax parser
//		saxParser.parseXML(xmlFile);
		
		// test out stax parser
		String names = staxDataFinder.findData(xmlFile, "firstName");
		System.out.println(names);
	}

}
