package com.example;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentLDAP {

	int etu_NIP;
	int etu_civilite;
	String etu_nom;
	String etu_prenom;
	String etu_maritale;
	String etu_codenationalite;
	String etu_email;

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

	public String getEtu_maritale() {
		return etu_maritale;
	}

	public void setEtu_maritale(String etu_maritale) {
		this.etu_maritale = etu_maritale;
	}

	public String getEtu_codenationalite() {
		return etu_codenationalite;
	}

	public void setEtu_codenationalite(String etu_codenationalite) {
		this.etu_codenationalite = etu_codenationalite;
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
				+ ", etu_prenom=" + etu_prenom + ", etu_maritale=" + etu_maritale + ", etu_codenationalite="
				+ etu_codenationalite + ", etu_email=" + etu_email + "]";
	}

	
	
}
