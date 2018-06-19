package main.java.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import lejos.nxt.ADSensorPort;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import main.java.domain.CapteurPackage.CaptLum;
import main.java.domain.CapteurPackage.CaptMouv;
import main.java.domain.LumierePackage.Interrupteur;
import main.java.domain.LumierePackage.SimulateurLum;
import main.java.infrastructure.ConfigCour;
import main.java.infrastructure.ConfigStandard;
import main.java.infrastructure.XMLParser;
import main.java.utils.Minuteur;
import java.lang.*;

public class SysControlDLC {
	private String T1;
	private String T2;
	private String T3;
	private ArrayList<SimulateurLum> listeSimulateurDeLumiere;
	private CaptMouv capteurMouvement;
	private CaptLum capteurLumiere;
	private Minuteur minuteur;
	private ConfigCour configCourante;
	private ConfigStandard configStandard;
	private Interrupteur interStop;
	//entrées de la brique
	private ADSensorPort p1 = SensorPort.S1;
	private ADSensorPort p2 = SensorPort.S2;
	private ADSensorPort p3 = SensorPort.S3 ;
	private ADSensorPort p4 = SensorPort.S4;
	private XMLParser xml; //toute interraction avec le config.XML passera par xml
	
	
	public SysControlDLC() throws ParserConfigurationException {
		this.capteurLumiere = new CaptLum(this.p1); //association capteur au port 1 de la brique
		this.capteurMouvement = new CaptMouv(this.p2);//association capteur au port 2 de la brique
		this.interStop = new Interrupteur(p3); //interrupteur au port 3 de la brique
		//initialisation des valeurs par rapport au xml
		this.xml = new XMLParser();
		this.T1 = this.xml.getT1();
		this.T2 = this.xml.getT2();
		this.T3 = this.xml.getT3();
		this.configCourante = this.xml.getconfCourante();
		this.configStandard = this.xml.getconfStandard();
	}
	
	public void startSystem() throws Exception {
		this.startConnectionBT(); //+comportement de la brique(message reçu)

	}
	
	public void allumerLumiere() {
		this.listeSimulateurDeLumiere.get(0).allumer();
	}
	
    public static void main(String[] args) throws Exception {
    	SysControlDLC sys = new SysControlDLC();
    	sys.startSystem();
    }
    
	public void startConnectionBT() throws Exception {
		boolean isrunning = true;

		// Main loop   
		while (true)
		{
		  LCD.drawString("waiting",0,0);
		  LCD.refresh();

		  // Listen for incoming connection

		  NXTConnection btc = Bluetooth.waitForConnection();

		  btc.setIOMode(NXTConnection.RAW);

		  LCD.clear();
		  LCD.drawString("connected",0,0);
		  LCD.refresh();  


		  // The InputStream for read data 
		  DataInputStream dis = btc.openDataInputStream();
		  DataOutputStream out = btc.openDataOutputStream();
		  
		// Loop for read data  
		  while (isrunning) {
	
		    String msg = dis.readUTF();
			  //byte msg[] = dis.readByte();
 
		    LCD.drawString(msg, 4, 4);
			
			//quelqu'un rentre dans la piece ?
			if(this.capteurMouvement.detectionMouv()) {
				LCD.drawString(Float.toString(this.capteurLumiere.detectionLum()), 0, 0);
			}		
			/*TODO : Evenement en fonction du message reçu (msg)*/
			//modif T1 : exemple de condition par msg reçu
			if(msg.indexOf("setT1") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//créer l'objet en question (ici juste une valeur pour T)
				// ajouter/modifier le xml : (il faut voir les méthodes dans XMLParser )
				this.xml.save(); //ecriture de la modification dans le xml
			}	
			//modif T2
			if(msg.indexOf("setT2")> -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//créer l'objet en question 
				// ajouter/modifier le xml : (il faut voir les méthodes dans XMLParser )
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//modif T3
			if(msg.indexOf("setT3")> -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//créer l'objet en question 
				// ajouter/modifier le xml : (il faut voir les méthodes dans XMLParser )
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//modif ConfigStandard
			if(msg.indexOf("setConfigStandard") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//créer l'objet en question 
				// ajouter/modifier le xml : (il faut voir les méthodes dans XMLParser )
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//modif ConfigCourante
			if(msg.indexOf("setConfigCour") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//créer l'objet en question 
				// ajouter/modifier le xml : (il faut voir les méthodes dans XMLParser )
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//envoyer T1 au smartphone
			if(msg.indexOf("sendT1") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//récupérer l'objet en question à l'aide du parser
				//out.writeUTF(); envoie de l'objet (faire une string du type <object/action>;<attribut1>;...;<attributN> :  T1;4 ou ConfigStandard;1;conf1;lum1)
				out.flush();
			}
			//envoyer T2 au smartphone
			if(msg.indexOf("sendT2") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//récupérer l'objet en question à l'aide du parser
				//out.writeUTF(); envoie de l'objet (faire une string du type <object/action>;<attribut1>;...;<attributN> :  T1;4 ou ConfigStandard;1;conf1;lum1)
				out.flush();
			}
			
			//envoyer T3 au smartphone
			if(msg.indexOf("sendT3") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//récupérer l'objet en question à l'aide du parser
				//out.writeUTF(); envoie de l'objet (faire une string du type <object/action>;<attribut1>;...;<attributN> :  T1;4 ou ConfigStandard;1;conf1;lum1)
				out.flush();
			}
			//envoyer ConfigStandard au smartphone
			if(msg.indexOf("sendConfigStandard") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//récupérer l'objet en question à l'aide du parser
				//out.writeUTF(); envoie de l'objet (faire une string du type <object/action>;<attribut1>;...;<attributN> :  T1;4 ou ConfigStandard;1;conf1;lum1)
				out.flush();
			}
			//envoyer ConfigCourante au smartphone
			if(msg.indexOf("sendConfigCourante") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//récupérer l'objet en question à l'aide du parser
				//out.writeUTF(); envoie de l'objet (faire une string du type <object/action>;<attribut1>;...;<attributN> :  T1;4 ou ConfigStandard;1;conf1;lum1)
				out.flush();
			}
			//envoyer toute les configAdmin(=configStandard avec isStandard= false) au smartphone
			if(msg.indexOf("sendAllConf") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//récupérer l'objet en question à l'aide du parser
				//out.writeUTF(); envoie de l'objet (faire une string du type <object/action>;<attribut1>;...;<attributN> :  T1;4 ou ConfigStandard;1;conf1;lum1)
				out.flush();
			}
			//envoyer toute les lumieres au smartphone
			if(msg.indexOf("sendAllLum") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//récupérer l'objet en question à l'aide du parser
				//out.writeUTF(); envoie de l'objet (faire une string du type <object/action>;<attribut1>;...;<attributN> :  T1;4 ou ConfigStandard;1;conf1;lum1) 
				// POUR TOUTES LES LUMIERES FAIRE ATTENTION A LA LONGUEUR DE CHAINE, peut être ne renvoyer que les 3 premières ?
				out.flush();
			}
			//allumer une lumiere
			if(msg.indexOf("on") > -1) {
				//découper le msg : faire regex pour découper la string par ";", du type "on;l" pour allumer lumoière ayant id=1
			}
			//ajouter une lumiere à une configuration
			if(msg.indexOf("addLumToConf") > -1) {
				//découper le msg : faire regex pour découper la string par ";" ==> "addLumToConf;<lum>;<conf>"
				//ajouter l'objet en question à l'aide du parser
			}
			//ajouter une lumière au systeme
			if(msg.indexOf("addLum") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//ajouter l'objet en question à l'aide du parser
			}
			//ajouter une configuration Admin (la confAdmin est une configStandard avec isStandard= false)
			if(msg.indexOf("addConf") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//ajouter l'objet en question à l'aide du parser
			}
			//supprimer une lumière au systeme
			if(msg.indexOf("deleteLum") > -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//ajouter l'objet en question à l'aide du parser
			}
			//ajouter une configuration Admin (la confAdmin est une configStandard avec isStandard= false)
			if(msg.indexOf("deleteConf")> -1) {
				//découper le msg : faire regex pour découper la string par ";"
				//ajouter l'objet en question à l'aide du parser
			}
			//Evenement extinction de la brique : pression sur interrupteur du port 3 de la brique
			if(this.interStop.pushed()) {
				isrunning = false;
			}
			/*FIN TODO*/
			LCD.refresh();
		  }
	
		  dis.close();
		  out.close();
		  // Wait for data to drain
			Thread.sleep(100);


		  LCD.clear();
		  LCD.drawString("closing",0,0);
		  LCD.refresh();

		  btc.close();

		  LCD.clear();
		}
	}
	
}
