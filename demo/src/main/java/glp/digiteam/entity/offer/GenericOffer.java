package glp.digiteam.entity.offer;

import javax.persistence.Entity;


@Entity
public class GenericOffer extends AbstractOffer implements Offer{

	
	private double remuneration;
	private String remunerationInfo;
	private String type;







	private String mission;
	private String skills;
	
	


	
	public GenericOffer(){
		
	}
	
	


	public double getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(double remuneration) {
		this.remuneration = remuneration;
	}



	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getRemunerationInfo() {
		return remunerationInfo;
	}

	public void setRemunerationInfo(String remunerationInfo) {
		this.remunerationInfo = remunerationInfo;
	}


	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	@Override
	public String toString() {
		return "GenericOffer [remuneration=" + remuneration + ", remunerationInfo=" + remunerationInfo 	+ ", mission=" + mission + ", skills=" + skills + "]";
	}


	
	
	
	
}
