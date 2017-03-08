package glp.digiteam.entity.offer;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Referent extends StaffLille1{




	@ManyToOne
	private ServiceEntity service ;
	
	
	@OneToMany(mappedBy="referent", cascade=CascadeType.ALL,targetEntity=AbstractOffer.class)
	private List<AbstractOffer> offers = new ArrayList<AbstractOffer>();


	public Referent(){
		
	}



	public ServiceEntity getService() {
		return service;
	}



	public void setService(ServiceEntity service) {
		this.service = service;
	}



	public List<AbstractOffer> getOffers() {
		return offers;
	}
	
	public void addOffer(AbstractOffer offer){
		this.offers.add(offer);
	}



	public void setOffers(List<AbstractOffer> offers) {
		this.offers = offers;
	}
	
	

}
