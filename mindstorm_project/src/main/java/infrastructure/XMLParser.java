package main.java.infrastructure;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;



public class XMLParser {
	
	private org.w3c.dom.Document doc;
	private NodeIterator noeud;
	
	   //On enregistre notre nouvelle arborescence dans le fichier
	   //d'origine dans un format classique.
	   private void save() throws Exception
	   {
		   XMLSerializer ser = new XMLSerializer();
		   ser.setOutputCharStream(new java.io.FileWriter("/root/git/mindstorm_JAVA/mindstorm_project/src/main/java/infrastructure/config.xml"));
		   ser.serialize(this.doc);
	   }
	   
	public XMLParser() throws ParserConfigurationException {

		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder loader = factory.newDocumentBuilder();
		try {
			this.doc = loader.parse("/root/git/mindstorm_JAVA/mindstorm_project/src/main/java/infrastructure/config.xml");
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
	//si -1 alors erreur
    public String getT1() {

        NodeList nList = doc.getElementsByTagName("T");

        String result = "-1";
        int i = 0;
        while ( nList.getLength() > i) {
        	org.w3c.dom.Node n = nList.item(i);
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	 

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n;
                String uid = elem.getAttribute("nom");

                if(uid.equals("T1")){

                     result=  elem.getAttribute("valeur");
                     System.out.println("User id: "+ uid + "value :" +result);

                }   
            }
           i++;
        }

        return result;
}

    public void setT1(String val) {

        NodeList nList = doc.getElementsByTagName("T");
        int i = 0;
        while ( nList.getLength() > i) {
        	org.w3c.dom.Node n = nList.item(i);
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	 

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n;
                String uid = elem.getAttribute("nom");

                if(uid.equals("T1")){
                     elem.setAttribute("valeur", val);
                }   
            }
           i++;
        }
}
    
    
    public ConfigCour getconfCourante() {

        NodeList nList = doc.getElementsByTagName("config-courante");

        int i = 0;
        ConfigCour c = null;
        while ( nList.getLength() > i) {
        	org.w3c.dom.Node n = nList.item(i);
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	 

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n;
                String uid = elem.getAttribute("nom");
                
                c = new ConfigCour(elem.getAttribute("id-config"),elem.getAttribute("nom"), elem.getAttribute("lumiereList"));
            }
           i++;
        }

        return c;
    }
    public ConfigCour setconfCourante(ConfigCour c) {

        NodeList nList = doc.getElementsByTagName("config-courante");

        int i = 0;
        while ( nList.getLength() > i) {
        	org.w3c.dom.Node n = nList.item(i);
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	 

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n;
                
                elem.setAttribute("id-config", c.getId_config());
                elem.setAttribute("nom",c.getNom());
                elem.setAttribute("lumiereList", c.getLumiereList());
            }
           i++;
        }

        return c;
    }
    
    public static void main(String[] args) throws Exception {
    	XMLParser xml = new XMLParser();
    	xml.setT1("2");
    	//xml.getT1();
    	//System.out.println(xml.getconfCourante().toString());
    	xml.setconfCourante(new ConfigCour("1","1","lum2"));
    	
    	xml.save(); // a faire après chaque changement
    	
    }
	
	
	
}
