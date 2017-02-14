package glp.digiteam.entity.offer;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class StandardOffer extends AbstractOffer implements Offer{

	
	private double remuneration;
	private String remunerationInfo;
	private String type;

	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date endDate;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date startDate;
	
	private int hoursPerWeek;
	




	private String mission;
	private String skills;
	
	


	
	public StandardOffer(){
		
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

	


	public Date getEndDate() {
		return endDate;
	}




	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}




	public Date getStartDate() {
		return startDate;
	}




	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	




	public int getHoursPerWeek() {
		return hoursPerWeek;
	}




	public void setHoursPerWeek(int hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}




	@Override
	public String toString() {
		return "GenericOffer [remuneration=" + remuneration + ", remunerationInfo=" + remunerationInfo 	+ ", mission=" + mission + ", skills=" + skills + "]";
	}


	
	
	
	
}
