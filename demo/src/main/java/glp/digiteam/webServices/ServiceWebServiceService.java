package glp.digiteam.webServices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;




@Service
public class ServiceWebServiceService {

	
private WebResource service;
	
	public ServiceWebServiceService() {
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(JacksonJsonProvider.class);
		Client clientWithJacksonSerializer = Client.create(cc);
		
		//Client client = Client.create(new DefaultClientConfig());
		
		service = clientWithJacksonSerializer.resource("http://adminieea.fil.univ-lille1.fr:8080/verlaine/rest");
	}
	
	public List<ServiceWebService> getServicesWS() throws JSONException {
        WebResource wr = service.path("/services");
        JSONArray jsonArray = (JSONArray) wr.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get(JSONArray.class);
        List<ServiceWebService> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            result.add(new ServiceWebService(jsonobject.getString("code"),jsonobject.getString("libelle")));
        }
        return result;
    }
	
}
