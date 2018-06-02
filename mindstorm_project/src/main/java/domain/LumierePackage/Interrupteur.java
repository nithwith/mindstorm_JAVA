package main.java.domain.LumierePackage;

import lejos.nxt.ADSensorPort;
import lejos.nxt.TouchSensor;
import main.java.service.SysControlDLC;

public class Interrupteur extends Thread{

	private TouchSensor interrupt;
	private boolean io = false;

	public void ioActive() {
	   io = true;
	}
	
	public Interrupteur(ADSensorPort port) {
		interrupt = new TouchSensor(port);
	}
	
	 public void run() {
		 if(this.interrupt.isPressed()) {
		 }
	 }

}
