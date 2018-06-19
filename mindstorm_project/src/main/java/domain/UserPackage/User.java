package main.java.domain.UserPackage;

public class User {
	private String id;
	private String login;
	private String MDP;
	private String isAdmin;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMDP() {
		return MDP;
	}
	public void setMDP(String mDP) {
		MDP = mDP;
	}
	public User(String id, String login, String mDP, String isAdmin) {
		super();
		this.id = id;
		this.login = login;
		MDP = mDP;
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", MDP=" + MDP + ", isAdmin=" + isAdmin + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}


}
