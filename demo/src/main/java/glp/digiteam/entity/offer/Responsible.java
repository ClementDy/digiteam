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
public class Responsible {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String firstName;
	private String lastName;
	
	private String email;
	private int phone;
	
	@OneToMany(mappedBy="responsible", /*cascade=CascadeType.ALL,*/targetEntity=AbstractOffer.class)
	private List<AbstractOffer> offers = new ArrayList<AbstractOffer>();

	
	public Responsible(){
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
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
