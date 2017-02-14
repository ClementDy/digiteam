package glp.digiteam.entity.offer;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

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

	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date validityDate;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date creationDate=new Date();

	private String status;
	
	
	private String title;
	
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
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	


	public Service getService() {
		return service;
	}

	public void setService(Service service) {
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

	

}
