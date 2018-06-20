package main.java.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import lejos.nxt.ADSensorPort;
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
import java.lang.String;

public class SysControlDLC {
	private String T1;
	private String T2;
	private String T3;
	private CaptMouv capteurMouvement;
	private CaptLum capteurLumiere;
	private Minuteur minuteur;
	private ConfigCour configCourante;
	private ConfigStandard configStandard;
	private Interrupteur interStop;
	//entrées de la brique
	private ADSensorPort p1 = SensorPort.S1;
	private ADSensorPort p2 = SensorPort.S2;
	private ADSensorPort p3 = SensorPort.S3;
	private ADSensorPort p4 = SensorPort.S4;
	private XMLParser xml; //toute interraction avec le config.XML passera par xml
	private Minuteur timer ;
	
	
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
		this.timer = new Minuteur();
	}
	
	public void startSystem() throws Exception {
		this.startConnectionBT(); //+comportement de la brique(message reçu)
	}
	
	/*public void allumerLumiere() {
		this.listeSimulateurDeLumiere.get(0).allumer();
	}
	*/
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
		  //envoie de la BD
		  DataOutputStream out = btc.openDataOutputStream();
		  String msg_out = this.creerMsgSync();
		  out.writeUTF(msg_out);  
		  out.flush();	
		  boolean isSomeOneInRoom = false;
		// Loop for read data  
		  while (isrunning) {
			
			  java.lang.String msg = dis.readUTF();
		    LCD.drawString(msg, 4, 4); //à supprimer : affichage du message recu sur la brique
			
			//quelqu'un rentre dans la piece ? (on considere qu'il n'y a qu'une personne)
			if(this.capteurMouvement.detectionMouv()) {
				timer.reset(); //on reset le timer à chaque entrée-sortie de personne dans la piece
				//la personne sort de la piece
				if(isSomeOneInRoom){
					isSomeOneInRoom = false; 
					
				}
				//la personne rentre dans la piece
				else{
					isSomeOneInRoom = true;
				}
				LCD.drawString(Float.toString(this.capteurLumiere.detectionLum()), 0, 0);
			}		
			//TODO : Evenement en fonction du message reçu (msg)
			//modif T1 : exemple de condition par msg reçu
			if(msg.indexOf("/sync")> -1) {
				String msg_out1 = this.creerMsgSync();
				out.writeUTF(msg_out1);  
				out.flush();		
			}
			if(msg.indexOf("/T1") > -1) {
				String[] tmp_part = msg.split(";");
				this.xml.setT1(tmp_part[1]);
				this.xml.save(); //ecriture de la modification dans le xml
			}	
			//modif T2
			if(msg.indexOf("/T2")> -1) {
				String[] tmp_part = msg.split(";");
				this.xml.setT2(tmp_part[1]);
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//modif T3
			if(msg.indexOf("/T3")> -1) {
				String[] tmp_part = msg.split(";");
				this.xml.setT3(tmp_part[1]);
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//modif ConfigStandard
			if(msg.indexOf("/ConfStandard") > -1) {
				String[] tmp_part = msg.split(";");
				String id_config = tmp_part[1];
				String nom = tmp_part[2];
				String lumiereList = "";
				int tmp = 3;
				for(int i=3;i<tmp_part.length-1;i++){ //debut de la liste des lumière : 3 Fin : Fin du tableau -1
					lumiereList += tmp_part[i];
					tmp++;
				}
				String isStandard =  tmp_part[tmp+1];
				this.xml.setconfStandard(new ConfigStandard(id_config,nom,lumiereList,isStandard));
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//modif ConfigCourante
			if(msg.indexOf("/ConfCour") > -1) {
				String[] tmp_part = msg.split(";");
				String id_config = tmp_part[1];
				String nom = tmp_part[2];
				String lumiereList = "";
				for(int i=3;i<tmp_part.length-1;i++){ //debut de la liste des lumière : 3 Fin : Fin du tableau -1
					lumiereList += tmp_part[i];
				}
				this.xml.setconfCourante(new ConfigCour(id_config,nom,lumiereList));
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//modif Config
			if(msg.indexOf("/Conf") > -1) {
				String[] tmp_part = msg.split(";");
				String id_config = tmp_part[1];
				String nom = tmp_part[2];
				String lumiereList = ""; 
				int tmp = 3;
				for(int i=3;i<tmp_part.length-1;i++){ //debut de la liste des lumière : 3 Fin : Fin du tableau -1
					lumiereList += tmp_part[i];
					tmp++;
				}
				String isStandard =  tmp_part[tmp+1];
				this.xml.setconfStandard(new ConfigStandard(id_config,nom,lumiereList,isStandard)); //Attention, on lance un objet confStandard avec isStandard=false (normalement)
				this.xml.save(); //ecriture de la modification dans le xml
			}
			if(msg.indexOf("/Lum") > -1) {
				String[] tmp_part = msg.split(";");
				this.xml.setLumiere(new SimulateurLum(tmp_part[1],tmp_part[2],tmp_part[3],tmp_part[4]));
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//allumer une lumiere
			if(msg.indexOf("/on") > -1) {
				String[] tmp_part = msg.split(";");
				String id = tmp_part[1];
				SimulateurLum l_tmp = this.xml.getLumiere(id);
				this.xml.setLumiere(new SimulateurLum( id,l_tmp.getType(),"true",l_tmp.getIntensite()));
				//TODO : Allumer la lumiere ayant l'ID 1 ou 2 (mettre en dur les ports de sortie)
			}
			if(msg.indexOf("/off") > -1) {
				String[] tmp_part = msg.split(";");
				String id = tmp_part[1];
				SimulateurLum l_tmp = this.xml.getLumiere(id);
				this.xml.setLumiere(new SimulateurLum(id,l_tmp.getType(),"false",l_tmp.getIntensite()));
				//TODO : Eteindre la lumiere ayant l'ID 1 ou 2 (mettre en dur les ports de sortie)
			}
			//ajouter une configuration Admin (la confAdmin est une configStandard avec isStandard= false)
			if(msg.indexOf("/addConf") > -1) {
				String[] tmp_part = msg.split(";");
				String id_config = tmp_part[1];
				String nom = tmp_part[2];
				String lumiereList = "";
				int tmp=3;
				for(int i=3;i<tmp_part.length-1;i++){ //debut de la liste des lumière : 3 Fin : Fin du tableau -1
					lumiereList += tmp_part[i];
					tmp++;
				}
				String isStandard =  tmp_part[tmp+1];
				this.xml.addconfAdmin(new ConfigStandard(id_config,nom,lumiereList,isStandard));
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//ajouter une configuration Admin (la confAdmin est une configStandard avec isStandard= false)
			if(msg.indexOf("/deleteConf")> -1) {
				String[] tmp_part = msg.split(";");
				String id_config = tmp_part[1];
				this.xml.deleteconfAdmin(id_config);
				this.xml.save(); //ecriture de la modification dans le xml
			}
			//Evenement extinction de la brique : pression sur interrupteur du port 3 de la brique
			if(this.interStop.pushed()) {
				isrunning = false;
			}
			
			// T2 : temps après T1 durant lequel la configuration lumineuse standard est mise en place. (la personne est sortie depuis T1+T2 de la piece)
			if(timer.getStart() +(Long.parseLong(this.xml.getT2())*1000) +(Long.parseLong(this.xml.getT1())*1000) < timer.now() && isSomeOneInRoom==false){
				this.xml.setconfCourante(this.xml.getconfStandard().transformIntoConfigCour());	
			}
			// T3 : Si aucun mouvement détecté dans la pièce après T3, les lumières de la pièce s'éteignent.
			if(timer.getStart() +(Long.parseLong(this.xml.getT2())*1000) +(Long.parseLong(this.xml.getT1())*1000  +(Long.parseLong(this.xml.getT3())*1000)) < timer.now() && isSomeOneInRoom==false){
				//TODO : eteindre toutes les lumieres (donc les lumiere 1 et 2, attachée physiquement à la brique)
			}
			/*FIN TODO*/
			//supprimer une lumière au systeme
			if(msg.indexOf("/deleteLum") > -1) {
				//pas à faire pour la première version
			}
						//ajouter une lumiere à une configuration
			if(msg.indexOf("/addLumToConf") > -1) {
				//pas à faire pour la première version
			}
			//ajouter une lumière au systeme
			if(msg.indexOf("/addLum") > -1) {
				//pas à faire pour la première version
			}
			LCD.clear();
		  LCD.drawString("Lumiere 1: "+this.xml.getLumiere("1").toString(),0,0); //affiche l'état de la lumiere ayant l'id 1 sous la forme "id;type;isAllume;intensite"
		  LCD.drawString("Lumiere 2: "+this.xml.getLumiere("2").toString(),0,1); //affiche l'état de la lumiere ayant l'id 2 sous la forme "id;type;isAllume;intensite"
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
	
	public String creerMsgSync(){
		return xml.createMSG();
		
	}
	
	
}
