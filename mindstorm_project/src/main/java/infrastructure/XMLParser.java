package main.java.infrastructure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import main.java.domain.LumierePackage.SimulateurLum;
import main.java.domain.UserPackage.User;
import main.java.service.SysControlDLC;



public class XMLParser {
	private org.w3c.dom.Document doc;
	private NodeIterator noeud;
	
	   //On enregistre notre nouvelle arborescence dans le fichier
	   //d'origine dans un format classique.
	   public void save() throws Exception
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
     
	   	//si -1 alors erreur 
    public String getT2() { 


        NodeList nList = doc.getElementsByTagName("T"); 


        String result = "-1"; 
        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  


            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
                String uid = elem.getAttribute("nom"); 


                if(uid.equals("T2")){ 


                     result=  elem.getAttribute("valeur"); 
                     System.out.println("User id: "+ uid + "value :" +result); 


                }    
            } 
           i++; 
        } 


        return result; 
} 


    public void setT2(String val) { 


        NodeList nList = doc.getElementsByTagName("T"); 
        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  


            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
                String uid = elem.getAttribute("nom"); 


                if(uid.equals("T2")){ 
                     elem.setAttribute("valeur", val); 
                }    
            } 
           i++; 
        } 
} 

 	//si -1 alors erreur 
    public String getT3() { 


        NodeList nList = doc.getElementsByTagName("T"); 


        String result = "-1"; 
        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  


            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
                String uid = elem.getAttribute("nom"); 


                if(uid.equals("T3")){ 


                     result=  elem.getAttribute("valeur"); 
                     System.out.println("User id: "+ uid + "value :" +result); 


                }    
            } 
           i++; 
        } 


        return result; 
} 


    public void setT3(String val) { 


        NodeList nList = doc.getElementsByTagName("T"); 
        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  


            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
                String uid = elem.getAttribute("nom"); 


                if(uid.equals("T3")){ 
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
     
	    public ConfigStandard getconfStandard() { 
        NodeList nList = doc.getElementsByTagName("config-admin"); 


        int i = 0; 
        ConfigStandard c = null; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
           if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  


            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
                String isS = elem.getAttribute("isStandard"); 
              if(isS.equals("true")){
				    c = new ConfigStandard(elem.getAttribute("id-config"),elem.getAttribute("nom"), elem.getAttribute("lumiereList"), elem.getAttribute("isStandard")); 

			   }
            } 
           i++; 
       } 
        return c; 
    } 
	 	//config admin = config standard (avec isStandard == false)
    public ConfigStandard setconfStandard(ConfigStandard c) { 


       NodeList nList = doc.getElementsByTagName("config-admin"); 


        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
					
				 if(elem.getAttribute("isStandard").equals("true")){				
						elem.setAttribute("isStandard", "false");  
				 }

				if(elem.getAttribute("id-config").equals(c.getId_config())){
					elem.setAttribute("isStandard", "true");  
				}
            } 
           i++; 
        } 


        return c; 
    } 
	
    public void addconfAdmin(ConfigStandard c) { 

       NodeList nList = doc.getElementsByTagName("config-admin"); 
       
    	    Node n = nList.item(0);
    	        Node tmp = n.cloneNode(false) ; 	  
            	org.w3c.dom.Element elem = (Element) tmp; 
						elem.setAttribute("id-config", c.getId_config()); 
						elem.setAttribute("nom",c.getNom()); 
						elem.setAttribute("lumiereList", c.getLumiereList()); 
						elem.setAttribute("isStandard", "false"); 
				
						tmp = (org.w3c.dom.Node) elem;		
						n.getParentNode().appendChild(tmp);	
    	        
    	    }
	 
	 public void deleteconfAdmin(String id) { 

       NodeList nList = doc.getElementsByTagName("config-admin"); 

        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
					
				 if(elem.getAttribute("id-config").equals(id)){				
						n = (org.w3c.dom.Node) elem;	
						n.getParentNode().removeChild(n);
				 }

            } 
           i++; 
        } 

    } 
	 
	 	 public ConfigStandard getConfAdmin(ConfigStandard c) { 
	 
			NodeList nList = doc.getElementsByTagName("config-admin"); 
			ConfigStandard result = new ConfigStandard("","false","","");
			 int i = 0; 
			 while ( nList.getLength() > i) { 
				org.w3c.dom.Node n = nList.item(i); 
				 if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  
	 
					org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
						
					 if(elem.getAttribute("id-config").equals(c.getId_config())){				
						 result.setId_config(elem.getAttribute("id-config"));
						 result.setNom(elem.getAttribute("nom"));
						 result.setLumiereList(elem.getAttribute("lumiereList") ); 
						result.setIsStandard( elem.getAttribute("isStandard"));
					 }

				 } 
				i++; 
			 }
			 return result;

		} 
	 
	 	public ArrayList<ConfigStandard> getAllconfAdmin() { 

       NodeList nList = doc.getElementsByTagName("config-admin"); 
		ArrayList <ConfigStandard> resultList = new ArrayList();
        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  
            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
            	ConfigStandard c = new ConfigStandard(elem.getAttribute("id-config"),elem.getAttribute("nom"), elem.getAttribute("lumiereList"), elem.getAttribute("isStandard"));
				resultList.add(c);
            } 
           i++; 

        } 
		return resultList;
    }

		public User getAdmin(){

        NodeList nList = doc.getElementsByTagName("user"); 

        User result = new User("","","",""); 
        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
                String isAdmin = elem.getAttribute("isAdmin"); 

                if(isAdmin.equals("true")){ 
                     result.setId(elem.getAttribute("id-u"));
					  result.setIsAdmin(isAdmin);
					  result.setLogin(elem.getAttribute("login"));
					  result.setMDP(elem.getAttribute("motDePasse"));

                }    
            } 
           i++; 
        } 
        return result; 
} 

		public void setAdmin(User u){

        NodeList nList = doc.getElementsByTagName("user"); 


        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
                String isAdmin = elem.getAttribute("isAdmin"); 

                if(isAdmin.equals("true")){ 
 						elem.setAttribute("id-u", u.getId()); 
						elem.setAttribute("login",u.getMDP()); 
						elem.setAttribute("motDePasse", u.getLogin()); 

                }    
            } 
           i++; 
        } 
	}
	  
	public SimulateurLum getLumiere(String id_l){
		  NodeList nList = doc.getElementsByTagName("lumiere"); 

        SimulateurLum result = new SimulateurLum("","","",""); 
        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
                String id = elem.getAttribute("id-lumiere"); 

                if(id.equals(id_l)){ 
                     result.setId(elem.getAttribute("id-lumiere"));
					  result.setIsAllume(elem.getAttribute("isAllume"));
					  result.setType(elem.getAttribute("typeLumiere"));
					  result.setIntensite(elem.getAttribute("intensite"));
                }    
            } 
           i++; 
        } 
        return result; 
	}
	
	//set : la lumiere existe dejà
	public void setLumiere(SimulateurLum l){
		 NodeList nList = doc.getElementsByTagName("lumiere"); 

        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
                String id = elem.getAttribute("id-lumiere"); 

                if(id.equals(l.getId() )){ 
                     elem.setAttribute("id-lumiere", l.getId());
					  elem.setAttribute("isAllume",l.getIsAllume());
					  elem.setAttribute("typeLumiere", l.getType());
					  elem.setAttribute("intensite", l.getIntensite());
                }    
            } 
           i++; 
        } 
	}
	
	public ArrayList<SimulateurLum> getAllLumiere(){
		NodeList nList = doc.getElementsByTagName("lumiere"); 
		ArrayList <SimulateurLum> resultList = new ArrayList();
        int i = 0; 
        
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  
            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
				SimulateurLum simlum = new SimulateurLum(elem.getAttribute("id-lumiere"),elem.getAttribute("typeLumiere"), elem.getAttribute("isAllume"), elem.getAttribute("intensite"));
				resultList.add(simlum);
            } 
           i++; 
			
        } 
		return resultList;
	}
	  
	  //marche si il y au moins une lumiere
	public void addLumiere(SimulateurLum l){
		NodeList nList =  doc.getElementsByTagName("list-lumiere");  
  	    	Node n = nList.item(0);
  	        Node tmp = n.getLastChild().cloneNode(false) ; 	  
            	org.w3c.dom.Element elem = (Element) tmp; 
                   elem.setAttribute("id-lumiere", l.getId());
					  elem.setAttribute("isAllume",l.getIsAllume());
					  elem.setAttribute("typeLumiere", l.getType());
					  elem.setAttribute("intensite", l.getIntensite());
				
					  tmp = (org.w3c.dom.Node) elem;		
					  n.appendChild(tmp);	
					 
	}
	
	public void addLumiereIntoConf(SimulateurLum l, ConfigStandard confS){

       NodeList nList = doc.getElementsByTagName("config-admin"); 

        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
					
				 if(elem.getAttribute("id-config").equals(confS.getId_config())){				
						elem.setAttribute("lumiereList",elem.getAttribute("lumiereList")+";"+l.getId());	
				 }

            } 
           i++; 
        } 		
	}
	
	public void deleteLumiere(SimulateurLum c) { 

       NodeList nList = doc.getElementsByTagName("lumiere"); 

        int i = 0; 
        while ( nList.getLength() > i) { 
        	org.w3c.dom.Node n = nList.item(i); 
            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {	  

            	org.w3c.dom.Element elem = (org.w3c.dom.Element) n; 
					
				 if(elem.getAttribute("id-lumiere").equals(c.getId())){				
						n = (org.w3c.dom.Node) elem;	
						n.getParentNode().removeChild(n);
				 }

            } 
           i++; 
        } 

    } 
	  
    public static void main(String[] args) throws Exception { 
    	XMLParser xml = new XMLParser(); 
    	xml.setT1("2"); 
    	//xml.getT1(); 
    	//System.out.println(xml.getconfCourante().toString()); 
    	//xml.setconfCourante(new ConfigCour("1","conf40","lum2")); 
		//xml.addconfAdmin(new ConfigStandard("2","conf3","lum2", "false"));
		//xml.setconfStandard(new ConfigStandard("2","conf3","lum2","")); 
		xml.addconfAdmin(new ConfigStandard("4","conf4","lum2", "false"));
		//xml.deleteconfAdmin(xml.getConfAdmin(new ConfigStandard("3","conf3","lum2", "false")).getId_config());
    	//System.out.println(xml.getAllconfAdmin().toString());
		
		//xml.addLumiere(new SimulateurLum("6", "plafonnier","true", "0"));
		//xml.addLumiereIntoConf(xml.getLumiere("5"), xml.getconfStandard());
		//xml.deleteLumiere(new SimulateurLum("5", "plafonnier","true", "0"));
		//System.out.println(xml.getAllLumiere().toString());
		
    	//System.out.println(xml.getAdmin().toString());
    	xml.setAdmin(new User("2","true","admin","123"));
		System.out.println(xml.createMSG());
		
    	xml.save(); // a faire après chaque changement
    	
    	 
    }

	public String createMSG() {
		String result = "";
		result += "/T1;"+this.getT1()+";";
		result += "/T2;"+this.getT2()+";";
		result += "/T3;"+this.getT3()+";";
		ArrayList <SimulateurLum> simlumList = this.getAllLumiere();
		for(int i=0; i<simlumList.size();i++){ //ou limite Lumiere Max à envoyé à la place de tout l'ArrayList
			SimulateurLum simlum = simlumList.get(i);
			result += "/Lum;"+simlum.toString()+";";
		}
		result += "/ConfStandard;"+this.getconfStandard().toString()+";";
		result += "/ConfCour;"+this.getconfCourante().toString()+";";
		
		ArrayList <ConfigStandard> confList = this.getAllconfAdmin();
		for(int i=0; i<confList.size();i++){ //ou limite Lumiere Max à envoyé à la place de tout l'ArrayList
			ConfigStandard conf_tmp = confList.get(i);
			result += "/Conf;"+conf_tmp.toString()+";";
		}
		return result;
	} 
	
	
	
}
