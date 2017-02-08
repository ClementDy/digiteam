package glp.digiteam.entity.offer;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Referent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	

	@OneToMany(mappedBy="referent", cascade=CascadeType.ALL)
	private List<Responsible> responsible = new ArrayList<Responsible>();

	
	
	
	public Referent(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public List<Responsible> getResponsible() {
		return responsible;
	}

	public void setResponsible(List<Responsible> responsible) {
		this.responsible = responsible;
	}
	
	public void addResponsible(Responsible responsible){
		this.responsible.add(responsible);
	}
	
	
	
	


}
