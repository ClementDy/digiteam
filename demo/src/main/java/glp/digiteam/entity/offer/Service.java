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

@Entity
public class Service {

	@Id
	private String code;
	
	String libelle;


	@OneToMany(mappedBy="service", cascade=CascadeType.ALL,targetEntity=AbstractOffer.class)
	private List<Offer> offers = new ArrayList<>();
	
	public Service(){
		
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



	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}


	
	
	
}
