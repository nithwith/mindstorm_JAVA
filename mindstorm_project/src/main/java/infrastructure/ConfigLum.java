package main.java.infrastructure;

import java.util.ArrayList;

import main.java.domain.LumierePackage.Lumiere;

public class ConfigLum {
	
	private int id_config;
	private String nom;
	private ArrayList<Lumiere> lumiereList;
	
	
	public ConfigLum(int id_config, String nom, ArrayList<Lumiere> lumiereList) {
		super();
		this.id_config = id_config;
		this.nom = nom;
		this.lumiereList = lumiereList;
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
