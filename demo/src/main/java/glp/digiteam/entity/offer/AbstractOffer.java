package glp.digiteam.entity.offer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class AbstractOffer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	

	@ManyToOne
	private Responsible responsible;

	@ManyToOne
	private Service service;

	
	
	
	public AbstractOffer(){
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public Responsible getResponsible() {
		return responsible;
	}

	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;
	}
	


	


	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	

	

}
