package glp.digiteam.entity.offer;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Moderator extends StaffLille1{



	@ManyToOne
	private Administrator administrator;
	
	
	public Moderator(){
		
	}


	public Administrator getAdministrator() {
		return administrator;
	}


	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	
	

}
