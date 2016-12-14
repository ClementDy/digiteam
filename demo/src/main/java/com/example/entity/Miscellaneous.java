package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Miscellaneous {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	private boolean association;
	private String nameAssociation;
	private String itKnowledge;
	private String languages;
	private String otherFormations;
	
	
	public Miscellaneous() {
		// TODO Auto-generated constructor stub
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
