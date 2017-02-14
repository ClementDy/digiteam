package glp.digiteam.webServices;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceWebService {
	
	
	private String code;
	private String libelle;
	
	public ServiceWebService(){
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
}
