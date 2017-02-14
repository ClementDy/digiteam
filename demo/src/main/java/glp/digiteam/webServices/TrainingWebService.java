package glp.digiteam.webServices;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrainingWebService {

	private int ins_DIPLOME;
	private String ins_LIBDIPLOME;
	private int ins_PARCOURS;
	private int ins_ANNEE;
	private int ins_NIP;
	private String ins_LIBPARCOURS;
	private String ins_NIVEAU;
	
	public TrainingWebService(){
		
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

	public int getIns_ANNEE() {
		return ins_ANNEE;
	}

	public void setIns_ANNEE(int ins_ANNEE) {
		this.ins_ANNEE = ins_ANNEE;
	}

	public int getIns_NIP() {
		return ins_NIP;
	}

	public void setIns_NIP(int ins_NIP) {
		this.ins_NIP = ins_NIP;
	}

	public String getIns_LIBPARCOURS() {
		return ins_LIBPARCOURS;
	}

	public void setIns_LIBPARCOURS(String ins_LIBPARCOURS) {
		this.ins_LIBPARCOURS = ins_LIBPARCOURS;
	}

	public String getIns_NIVEAU() {
		return ins_NIVEAU;
	}

	public void setIns_NIVEAU(String ins_NIVEAU) {
		this.ins_NIVEAU = ins_NIVEAU;
	}

	@Override
	public String toString() {
		return "TrainingLDAP [ins_LIBPARCOURS=" + ins_LIBPARCOURS + ", ins_ANNEE=" + ins_ANNEE + ", ins_NIVEAU="
				+ ins_NIVEAU + ", ins_NIP=" + ins_NIP + ", ins_DIPLOME=" + ins_DIPLOME + ", ins_LIBDIPLOME="
				+ ins_LIBDIPLOME + ", ins_PARCOURS=" + ins_PARCOURS + "]";
	}
}
