package glp.digiteam.entity.offer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Administrator extends StaffLille1{
	
	
	
	@OneToMany(mappedBy="administrator", cascade=CascadeType.ALL)
	private List<Moderator> responsible = new ArrayList<Moderator>();

	
	public Administrator(){
		
	}


	public List<Moderator> getResponsible() {
		return responsible;
	}


	public void setResponsible(List<Moderator> responsible) {
		this.responsible = responsible;
	}
	
	

}
