package glp.digiteam.ldap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentLDAP {

	private int etu_NIP;
	private int etu_civilite;
	private String etu_nom;
	private String etu_prenom;
	private String etu_marital;
	private String etu_codenationalite;
	private String etu_libnationalite;
	private String etu_email;

	public StudentLDAP(){
		
	}

	public int getEtu_NIP() {
		return etu_NIP;
	}

	public void setEtu_NIP(int etu_NIP) {
		this.etu_NIP = etu_NIP;
	}

	public int getEtu_civilite() {
		return etu_civilite;
	}

	public void setEtu_civilite(int etu_civilite) {
		this.etu_civilite = etu_civilite;
	}

	public String getEtu_nom() {
		return etu_nom;
	}

	public void setEtu_nom(String etu_nom) {
		this.etu_nom = etu_nom;
	}

	public String getEtu_prenom() {
		return etu_prenom;
	}

	public void setEtu_prenom(String etu_prenom) {
		this.etu_prenom = etu_prenom;
	}

	public String getEtu_marital() {
		return etu_marital;
	}

	public void setEtu_marital(String etu_marital) {
		this.etu_marital = etu_marital;
	}

	public String getEtu_codenationalite() {
		return etu_codenationalite;
	}

	public void setEtu_codenationalite(String etu_codenationalite) {
		this.etu_codenationalite = etu_codenationalite;
	}

	public String getEtu_libnationalite() {
		return etu_libnationalite;
	}

	public void setEtu_libnationalite(String etu_libnationalite) {
		this.etu_libnationalite = etu_libnationalite;
	}

	public String getEtu_email() {
		return etu_email;
	}

	public void setEtu_email(String etu_email) {
		this.etu_email = etu_email;
	}

	@Override
	public String toString() {
		return "StudentLDAP [etu_NIP=" + etu_NIP + ", etu_civilite=" + etu_civilite + ", etu_nom=" + etu_nom
				+ ", etu_prenom=" + etu_prenom + ", etu_marital=" + etu_marital + ", etu_codenationalite="
				+ etu_codenationalite + ", etu_email=" + etu_email + "]";
	}
}
