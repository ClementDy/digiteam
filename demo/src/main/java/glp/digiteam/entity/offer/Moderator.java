package glp.digiteam.entity.offer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Moderator extends StaffLille1{



	@ManyToOne
	private Administrator administrator;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="moderator", cascade=CascadeType.ALL)
	private List<Referent> referents = new ArrayList<Referent>();

	
	
	public Moderator(){
		
	}


	public Administrator getAdministrator() {
		return administrator;
	}


	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}


	public List<Referent> getReferents() {
		return referents;
	}


	public void setReferents(List<Referent> referent) {
		this.referents = referent;
	}


	public void addReferent(Referent referent) {
		this.referents.add(referent);		
	}
	
	

}
