package main.java.utils;

public class Minuteur {
	private long start;

	public Minuteur(){
		this.start = System.currentTimeMillis();
	}
	
	public void reset(){
		this.start = System.currentTimeMillis();
	}
	
	public long now(){
		return System.currentTimeMillis();
	}
	
	public long getStart() {
		return start;
	}
	public void setStart(long s) {
		this.start = s;
	}
}
