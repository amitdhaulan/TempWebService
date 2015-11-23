package amit.websevices.client;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ConversionServiceClient {
	static final String REST_URI = "http://localhost:7070/TempWebService";
	static final String CELTOFAR = "/ConversionService/cel2far/";
	static final String FARTOCEL = "/ConversionService/far2cel/";

	public static void main(String[] args) {

		double CEL = 36.0;
		double FAR = 98.0;

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(REST_URI);

		WebResource addService = service.path("rest").path(CELTOFAR + CEL);
		System.out.println("CEL_TO_FAR Response: " + getResponse(addService));
		System.out.println("CEL_TO_FAR Output as JSON: "
				+ getOutputAsJSON(addService));
		System.out
				.println("---------------------------------------------------");

		WebResource subService = service.path("rest").path(FARTOCEL + FAR);
		System.out.println("FAR_TO_CEL Response: " + getResponse(subService));
		System.out.println("FAR_TO_CEL Output as JSON: "
				+ getOutputAsJSON(subService));
		System.out
				.println("---------------------------------------------------");

	}

	private static String getResponse(WebResource service) {
		return service.accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class).toString();

	}

	private static String getOutputAsJSON(WebResource service) {
		return service.accept(MediaType.APPLICATION_JSON).get(String.class);

	}

}