package main.java.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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
import main.java.utils.Minuteur;

public class SysControlDLC {
	private int T1;
	private int T2;
	private int T3;
	private ArrayList<SimulateurLum> listeSimulateurDeLumiere;
	private CaptMouv capteurMouvement;
	private CaptLum capteurLumiere;
	private Minuteur minuteur;
	private ConfigCour configCourante;
	private ConfigStandard configStandard;
	private Interrupteur interStop;
	private Interrupteur interStart;
	private ADSensorPort p1 = SensorPort.S1;
	private ADSensorPort p2 = SensorPort.S2;
	private ADSensorPort p3 = SensorPort.S3 ;
	private ADSensorPort p4 = SensorPort.S4;
	
	
	public SysControlDLC() {
		this.capteurLumiere = new CaptLum(this.p1);
		this.capteurMouvement = new CaptMouv(this.p2);
		this.interStop = new Interrupteur(p3);
	}
	
	public void startSystem() throws IOException, InterruptedException {
		this.startConnectionBT();
		while(true) {
			if(this.capteurMouvement.detectionMouv()) {
				LCD.drawString(Float.toString(this.capteurLumiere.detectionLum()), 0, 0);
			}		
		}
	}
	
	public void allumerLumiere() {
		this.listeSimulateurDeLumiere.get(0).allumer();
	}
	
    public static void main(String[] args) throws IOException, InterruptedException {
    	SysControlDLC sys = new SysControlDLC();
    	sys.startSystem();
    }
    
	public void startConnectionBT() throws IOException, InterruptedException {
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
		  //DataOutputStream out = btc.openDataOutputStream();
		  LCD.clear();
		  LCD.drawString("test", 1, 1);
		  LCD.refresh(); 
		LCD.clear(); 
		// Loop for read data  
		  while (isrunning) {
	
		    String n = dis.readUTF();
			  //byte n[] = dis.readByte();
 
		    LCD.drawString(n, 4, 4);

		  //  LCD.drawInt(n, 1, 1);
			//out.writeUTF("coucou théo");;
			//out.flush();
			LCD.refresh();
		  }
	
		  dis.close();
		  //out.close();
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
