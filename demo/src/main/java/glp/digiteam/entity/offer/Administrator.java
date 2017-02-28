package glp.digiteam.entity.offer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Administrator extends StaffLille1{
	
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="administrator", cascade=CascadeType.ALL)
	private List<Moderator> moderators = new ArrayList<Moderator>();

	
	public Administrator(){
		
	}


	public List<Moderator> getModerators() {
		return moderators;
	}


	public void setModerators(List<Moderator> moderators) {
		this.moderators = moderators;
	}
	
	public void addModerator(Moderator moderator){
		this.moderators.add(moderator);
	}
	
	public void removeModerator(String name){
		for(int i=0;i<moderators.size();i++){
			if(moderators.get(i).getName().equals(name)){
				moderators.remove(i);
			}
		}
	}

}
