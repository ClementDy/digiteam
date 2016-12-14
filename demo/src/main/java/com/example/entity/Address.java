package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String street;
	private String complement;
	private int postalCode;
	private String city;

	@OneToOne(mappedBy="address")
	@PrimaryKeyJoinColumn
	private Student student;


	public Address() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Address(String street, String complement, int postalCode, String city, Student student) {
		this.street = street;
		this.complement = complement;
		this.postalCode = postalCode;
		this.city = city;
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
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

}
