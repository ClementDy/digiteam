package com.example;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrainingLDAP {

	
	String ins_LIBPARCOURS;
	int ins_ANNEE;
	String ins_NIVEAU;
	int ins_NIP;
	int ins_DIPLOME;
	String ins_LIBDIPLOME;
	int ins_PARCOURS;
	
	public TrainingLDAP(){
		
	}

	public String getIns_LIBPARCOURS() {
		return ins_LIBPARCOURS;
	}

	public void setIns_LIBPARCOURS(String ins_LIBPARCOURS) {
		this.ins_LIBPARCOURS = ins_LIBPARCOURS;
	}

	public int getIns_ANNEE() {
		return ins_ANNEE;
	}

	public void setIns_ANNEE(int ins_ANNEE) {
		this.ins_ANNEE = ins_ANNEE;
	}

	public String getIns_NIVEAU() {
		return ins_NIVEAU;
	}

	public void setIns_NIVEAU(String ins_NIVEAU) {
		this.ins_NIVEAU = ins_NIVEAU;
	}

	public int getIns_NIP() {
		return ins_NIP;
	}

	public void setIns_NIP(int ins_NIP) {
		this.ins_NIP = ins_NIP;
	}

	public int getIns_DIPLOME() {
		return ins_DIPLOME;
	}

	public void setIns_DIPLOME(int ins_DIPLOME) {
		this.ins_DIPLOME = ins_DIPLOME;
	}

	public String getIns_LIBDIPLOME() {
		return ins_LIBDIPLOME;
	}

	public void setIns_LIBDIPLOME(String ins_LIBDIPLOME) {
		this.ins_LIBDIPLOME = ins_LIBDIPLOME;
	}

	public int getIns_PARCOURS() {
		return ins_PARCOURS;
	}

	public void setIns_PARCOURS(int ins_PARCOURS) {
		this.ins_PARCOURS = ins_PARCOURS;
	}

	@Override
	public String toString() {
		return "TrainingLDAP [ins_LIBPARCOURS=" + ins_LIBPARCOURS + ", ins_ANNEE=" + ins_ANNEE + ", ins_NIVEAU="
				+ ins_NIVEAU + ", ins_NIP=" + ins_NIP + ", ins_DIPLOME=" + ins_DIPLOME + ", ins_LIBDIPLOME="
				+ ins_LIBDIPLOME + ", ins_PARCOURS=" + ins_PARCOURS + "]";
	}
	
	
}
