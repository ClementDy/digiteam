package glp.digiteam.entity.student;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class Miscellaneous {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@OneToOne
	private Student student;
	
	private boolean association;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String nameAssociation;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String itKnowledge;
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String languages;
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String otherFormations;
	
	
	public Miscellaneous() {
		
	}
	
	public Miscellaneous(Student student) {
		this.student = student;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	

	public boolean isAssociation() {
		return association;
	}

	public void setAssociation(boolean association) {
		this.association = association;
	}

	public String getNameAssociation() {
		return nameAssociation;
	}

	public void setNameAssociation(String nameAssociation) {
		this.nameAssociation = nameAssociation;
	}

	public String getItKnowledge() {
		return itKnowledge;
	}

	public void setItKnowledge(String itKnowledge) {
		this.itKnowledge = itKnowledge;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getOtherFormations() {
		return otherFormations;
	}

	public void setOtherFormations(String otherFormations) {
		this.otherFormations = otherFormations;
	}
}
