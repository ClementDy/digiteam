package glp.digiteam.entity.offer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class StaffLille1 {
	
	@Id
	private String email;
	
	private String lastName;
	private String firstName;
	
	private String phone;
	

	@ManyToOne
	private ServiceEntity service ;
	
	
	@OneToMany(mappedBy="referent", cascade=CascadeType.ALL,targetEntity=AbstractOffer.class)
	private List<AbstractOffer> offers = new ArrayList<AbstractOffer>();

	
	private boolean isModerator;
	private boolean isAdministrator;
	private boolean isReferent;

	public StaffLille1(){
		
	}

	

	public boolean isModerator() {
		return isModerator;
	}

	public void setModerator(boolean isModerator) {
		this.isModerator = isModerator;
	}

	public boolean isAdministrator() {
		return isAdministrator;
	}

	public void setAdministrator(boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	public boolean isReferent() {
		return isReferent;
	}

	public void setReferent(boolean isReferent) {
		this.isReferent = isReferent;
	}
	
	
	public void addOffer(AbstractOffer offer){
		this.offers.add(offer);
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String fistName) {
		this.firstName = fistName;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
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



	public void setOffers(List<AbstractOffer> offers) {
		this.offers = offers;
	}


	
	


}
