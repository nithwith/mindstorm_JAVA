package main.java.infrastructure;
import java.util.ArrayList;

import main.java.domain.LumierePackage.*;

public class ConfigCour {

	private int id_config;
	private String nom;
	private ArrayList<Lumiere> lumiereList;
	
	
	public ConfigCour(int id_config, String nom) {
		
		super();
		this.id_config = id_config;
		this.nom = nom;
	}
	
	public int getId_config() {
		return id_config;
	}
	public void setId_config(int id_config) {
		this.id_config = id_config;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Lumiere> getLumiereList() {
		return lumiereList;
	}
	public void setLumiereList(ArrayList<Lumiere> lumiereList) {
		this.lumiereList = lumiereList;
	}
	
}
