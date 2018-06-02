package main.java.domain.CapteurPackage;

import lejos.nxt.ADSensorPort;
import lejos.nxt.LightSensor;

public class CaptLum extends LightSensor implements Capteur{

	public CaptLum(ADSensorPort port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	public float detectionLum() {
		return this.getLightValue();
	}
	
}
