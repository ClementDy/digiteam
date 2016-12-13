package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

//@Entity
public class Address {

	private String street;
	private String complement;
	private int postalCode;
	private String City;

/*	@OneToOne(mappedBy="otherInfo")
	@PrimaryKeyJoinColumn*/
	private Student student;


	public Address() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Address(java.lang.String street, java.lang.String complement, int postalCode, java.lang.String city,
			Student student) {
		super();
		this.street = street;
		complement = complement;
		this.postalCode = postalCode;
		City = city;
		this.student = student;
	}



	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getString() {
		return complement;
	}
	public void setString(String string) {
		complement = string;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

}
