package main.java.domain.CapteurPackage;

import lejos.nxt.ADSensorPort;
import lejos.nxt.I2CPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

public class CaptMouv extends TouchSensor implements Capteur{

	public CaptMouv(ADSensorPort port) {
		super(port);
		// TODO Auto-generated constructor stub
	}
	
	public boolean detectionMouv() {
		return this.isPressed();
	}

}
