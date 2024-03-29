package glp.digiteam.entity.offer;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class AbstractOffer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	
	@Lob
	@Type(type="org.hibernate.type.TextType")
	private String mission;

	@ManyToOne
	private StaffLille1 referent;

	@ManyToOne
	private ServiceEntity service;

	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date validityDate;
	
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date moderationDate;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date creationDate=new Date();

	private String status;
	
	private String comment;
	
	private String title;
	
	private String lastNameResponsible;
	
	private String firstNameResponsible;
	
	private String phoneResponsible;
	
	private String emailResponsible;
	
	
	private double remuneration;
	private String remunerationInfo;
	private String type;
	

	
	@Lob
	@Type(type="org.hibernate.type.TextType")
	private String skills;
	
	
	public AbstractOffer(){
		
		
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public StaffLille1 getReferent() {
		return referent;
	}

	public void setResponsible(StaffLille1 referent) {
		this.referent = referent;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	


	public ServiceEntity getService() {
		return service;
	}

	public double getRemuneration() {
		return remuneration;
	}

	public void setRemuneration(double remuneration) {
		this.remuneration = remuneration;
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

	
	public void setService(ServiceEntity service) {
		this.service = service;
	}


	public Date getValidityDate() {
		return validityDate;
	}


	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getModerationDate() {
		return moderationDate;
	}


	public void setModerationDate(Date moderationDate) {
		this.moderationDate = moderationDate;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getLastNameResponsible() {
		return lastNameResponsible;
	}


	public void setLastNameResponsible(String lastNameResponsible) {
		this.lastNameResponsible = lastNameResponsible;
	}


	public String getFirstNameResponsible() {
		return firstNameResponsible;
	}


	public void setFirstNameResponsible(String firstNameResponsible) {
		this.firstNameResponsible = firstNameResponsible;
	}


	public String getPhoneResponsible() {
		return phoneResponsible;
	}


	public void setPhoneResponsible(String phoneResponsible) {
		this.phoneResponsible = phoneResponsible;
	}


	public String getEmailResponsible() {
		return emailResponsible;
	}


	public void setEmailResponsible(String emailResponsible) {
		this.emailResponsible = emailResponsible;
	}


	public void setReferent(StaffLille1 referent) {
		this.referent = referent;
	}
	
	


}
