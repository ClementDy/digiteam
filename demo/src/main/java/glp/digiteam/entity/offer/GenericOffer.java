package glp.digiteam.entity.offer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class GenericOffer implements Offer{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private double remuneration;
	private String remunerationInfo;

	@OneToOne(mappedBy="offer", cascade=CascadeType.ALL)
	private Service service;

	private String title;
	private String mission;
	private String skills;
	
	
	@OneToOne(mappedBy="offer", cascade=CascadeType.ALL)
	private Referent referent;
	
	@OneToOne(mappedBy="offer", cascade=CascadeType.ALL)
	private Responsible responsible;

	
	public GenericOffer(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(double remuneration) {
		this.remuneration = remuneration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public Referent getReferent() {
		return referent;
	}

	public void setReferent(Referent referent) {
		this.referent = referent;
	}

	public String getRemunerationInfo() {
		return remunerationInfo;
	}

	public void setRemunerationInfo(String remunerationInfo) {
		this.remunerationInfo = remunerationInfo;
	}
	
	public Responsible getResponsible() {
		return responsible;
	}

	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	
	
	
	
}
