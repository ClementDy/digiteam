package glp.digiteam.entity.offer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class ServiceEntity {

	@Id
	private String code;
	
	private String libelle;


	@OneToMany(mappedBy="service", cascade=CascadeType.ALL,targetEntity=AbstractOffer.class)
	private List<StaffLille1> referents = new ArrayList<>();
	
	public ServiceEntity(){
		
	}
	
	

	public ServiceEntity(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
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



	public List<StaffLille1> getReferents() {
		return referents;
	}



	public void setReferents(List<StaffLille1> referents) {
		this.referents = referents;
	}



	


	
	
	
}
