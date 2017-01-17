package com.example.ldap;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Service
public class StudentLDAPService {

	private WebResource service;
	
	public StudentLDAPService() {
		Client client = Client.create(new DefaultClientConfig());
		
		service = client.resource("http://adminieea.fil.univ-lille1.fr:8080/verlaine/rest");
	}
	
	public StudentLDAP getStudentLDAP(long nip) {
		return service.path("/etudiant/"+nip).accept(MediaType.APPLICATION_JSON_TYPE).get(StudentLDAP.class);
	}
}
