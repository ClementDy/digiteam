package glp.digiteam.webServices;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Service
public class ServiceWebServiceService {

	
private WebResource service;
	
	public ServiceWebServiceService() {
		Client client = Client.create(new DefaultClientConfig());
		
		service = client.resource("http://adminieea.fil.univ-lille1.fr:8080/verlaine/rest");
	}
	
	public ServiceWebService getServices() {
		return  service.path("/services").accept(MediaType.APPLICATION_JSON_TYPE).get(ServiceWebService.class);
	}
	
}
