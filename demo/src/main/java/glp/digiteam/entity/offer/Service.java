package glp.digiteam.entity.offer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Service {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	String name;

	@OneToOne
	private GenericOffer offer;
	
	
	public Service(){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public GenericOffer getOffer() {
		return offer;
	}

	public void setOffer(GenericOffer offer) {
		this.offer = offer;
	}
	
	
	
}
