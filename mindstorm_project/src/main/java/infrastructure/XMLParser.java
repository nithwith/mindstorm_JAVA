package main.java.infrastructure;

import java.io.File;
import java.lang.annotation.Documented;

import lejos.robotics.pathfinding.Node;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.*;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;



public class XMLParser {
	
	private org.w3c.dom.Document doc;
	private NodeIterator noeud;
	
	
	public void init() throws ParserConfigurationException {

		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder loader = factory.newDocumentBuilder();
		try {
			this.doc = loader.parse("/root/git/mindstorm_JAVA/mindstorm_project/src/main/java/config.xml");
			DocumentTraversal traversal = (DocumentTraversal) this.doc;
			
			this.noeud  = traversal.createNodeIterator(this.doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
			
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public String getT1() {
		return "";
	}
	
	
	
}
