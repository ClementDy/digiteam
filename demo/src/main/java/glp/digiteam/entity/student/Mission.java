package glp.digiteam.entity.student;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mission {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	private String title;
	
	public Mission() {
		
	}
	
	public Mission(String title) {
		super();
		this.title = title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
