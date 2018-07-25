package de.example.xml.dom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Some simple tests to proof that the dom parser works quite right
 * 
 * @author Bastian Bräunel
 *
 */
class DomParserTest {

	private File xmlFile;
	private File xsdFile;
	private File faileFile;
	private DomParser domParser;

	@BeforeEach
	void setUp() throws Exception {
		xmlFile = new File("res/employee.xml");
		xsdFile = new File("res/employee_validator.xsd");
		faileFile = new File("res/empty.txt");

		domParser = new DomParser();
	}

	@Test
	void testGetDomFromFile() {
		Element rootElement = domParser.getDomFromFile(xmlFile);

		// <employees>
		// 		<employee id="222">
		// 		<firstName>Alex</firstName>
		// 		<lastName>Gussin</lastName>
		// 		<location>Russia</location>
		// 		</employee>
		//	    <employee id="888">
		//        <firstName>Hanny</firstName>
		//        <lastName>Schnmidt</lastName>
		//        <location>Germany</location>
		//      </employee>
		// </employees>

		// get all employee nodes
		NodeList nList = rootElement.getElementsByTagName("employee");
		
		assertEquals(nList.getLength(), 7);
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				// Treat the node as an element 
				Element eElement = (Element) node;
				// get the id of the element
				int eID = Integer.parseInt(eElement.getAttribute("id"));
				
				if (eID == 222) {
					assertEquals(eElement.getElementsByTagName("firstName").item(0).getTextContent(), "Alex");
				} else if (eID == 888) {
					assertNotEquals(eElement.getElementsByTagName("firstName").item(0).getTextContent(), "Alex");
					assertEquals(eElement.getElementsByTagName("firstName").item(0).getTextContent(), "Hanny");
				}

			}
		}
	}

	@Test
	void testIsValid() {
		assertTrue(domParser.isValid(xsdFile, xmlFile));

		assertFalse(domParser.isValid(null, null));
		assertFalse(domParser.isValid(null, xmlFile));
		assertFalse(domParser.isValid(xsdFile, null));

		assertFalse(domParser.isValid(xsdFile, faileFile));
		assertFalse(domParser.isValid(faileFile, xmlFile));
	}

}
