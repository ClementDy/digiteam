package glp.digiteam.ldap;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Service
public class TrainingLDAPService {

	private WebResource service;
	
	public TrainingLDAPService() {
		Client client = Client.create(new DefaultClientConfig());
		
		service = client.resource("http://adminieea.fil.univ-lille1.fr:8080/verlaine/rest");
	}
	
	public TrainingLDAP getTrainingLDAP(int year, long nip) {
		return service.path("/etudiant/"+year+"/"+nip).accept(MediaType.APPLICATION_JSON_TYPE).get(TrainingLDAP.class);
	}
}
