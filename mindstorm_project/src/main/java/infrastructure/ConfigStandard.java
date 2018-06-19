package main.java.infrastructure;

public class ConfigStandard {
	private String id_config;
	private String nom;
	private String lumiereList;
	private String isStandard;
	
	
	public ConfigStandard(String id_config, String nom, String lumiereList, String isS) {
		super();
		this.id_config = id_config;
		this.nom = nom;
		this.lumiereList = lumiereList;
		this.isStandard = isS;
	}
	
	public String getId_config() {
		return id_config;
	}
	public void setId_config(String id_config) {
		this.id_config = id_config;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLumiereList() {
		return lumiereList;
	}
	public void setLumiereList(String lumiereList) {
		this.lumiereList = lumiereList;
	}
	
	public String getIsStandard() {
		return isStandard;
	}
	public void setIsStandard(String isS) {
		this.isStandard = isS;
	}
	
	public ConfigCour transformIntoConfigCour(){
		return new ConfigCour(this.id_config, this.nom, this.lumiereList);
	}

	@Override
	public String toString() {
		return "ConfigStandard [id_config=" + id_config + ", nom=" + nom + ", lumiereList=" + lumiereList
				+ ", isStandard=" + isStandard + "]";
	}
}
