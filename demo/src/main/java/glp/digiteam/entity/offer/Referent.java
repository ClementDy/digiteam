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

import glp.digiteam.entity.student.ExternalContract;

@Entity
public class Referent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String lastName;
	
	
	@OneToMany(mappedBy="referent", cascade=CascadeType.ALL,targetEntity=AbstractOffer.class)
	private List<AbstractOffer> offers = new ArrayList<AbstractOffer>();

	
	
	public Referent(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<AbstractOffer> getOffers() {
		return offers;
	}

	public void setOffers(List<AbstractOffer> offers) {
		this.offers = offers;
	}
	
	public void addOffer(AbstractOffer offer){
		this.offers.add(offer);
	}

	
	
}
