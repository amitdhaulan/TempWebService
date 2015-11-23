package amit.webservice;

import java.text.DecimalFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/convert")
public class TemperatureConvertor {
	JSONObject json;
	JSONArray array;

	@GET
	@Path("/cel2far")
	@Produces(MediaType.APPLICATION_JSON)
	public String convertInchToFeet(@QueryParam("_cel") String _cel)
			throws JSONException {
		try {
			if (_cel.matches("[0-9.-]+")) {
				double cel = Double.parseDouble(_cel);
				double far = 0;
				far = (double) (((cel / 5) * 9) + 32);
				json = new JSONObject();
				array = new JSONArray();
				array.put("cel:" + cel);
				array.put("far:"
						+ Double.parseDouble(new DecimalFormat("#.##")
								.format(far)));
				// json.put("response:", array);
				json.put("response:", array);
				System.out.println(/*"GET RESPONSE: " + */json.toString());
				return json.toString();

			} else {
				json = new JSONObject();
				array = new JSONArray();
				return (json.put("response:", (array.put("error:"
						+ "provide numbers only!"))).toString());
			}

		} catch (Exception exception) {
			json = new JSONObject();
			array = new JSONArray();
			return (json.put("response:", (array.put("error:"
					+ "provide numbers only!"))).toString());
		}

	}

	@Path("/far2cel")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String convertFeetToInch(@QueryParam("_far") String _far)
			throws JSONException {

		try {

			if (_far != null) {
				if (!_far.equals("")) {
					if (_far.matches("[0-9.-]+")) {
						double cel = 0;
						double far = Double.parseDouble(_far);

						cel = (double) (((far - 32) / 9) * 5);

						JSONObject json = new JSONObject();
						JSONArray array = new JSONArray();
						array.put("far:" + far);
						array.put("cel:"
								+ Double.parseDouble(new DecimalFormat("#.##")
										.format(cel)));
						json.put("response:", array);
						return json.toString();
					} else {
						if (_far.matches("[a-zA-Z]+")) {
							json = new JSONObject();
							array = new JSONArray();
							return (json.put("response:", (array.put("error:"
									+ "provide numbers only!"))).toString());
						}
					}
				} else {

				}
			} else {
				json = new JSONObject();
				array = new JSONArray();
				return (json.put("response:", (array.put("error:"
						+ "no param values provided!"))).toString());
			}

		} catch (Exception exception) {
			json = new JSONObject();
			array = new JSONArray();
			return (json.put("response:", (array.put("error:"
					+ "provide numbers only!"))).toString());
		}
		json = new JSONObject();
		array = new JSONArray();
		return (json.put("response:", (array.put("error:"
				+ "provide numbers only!"))).toString());
	}

	@GET
	public String print(@PathParam("i") String str) {
		String form = "form";
		return "<html><head><title> Registration "
				+ form
				+ " in html </title></head><body><table ><tbody><tr><td ><form ><fieldset><label for=Name> Name: </label><input type=text name=name id=name /><br><label for=email> Email Id: </label><input type=text name=email-id id=email-id /><br><label for=username> Username: </label><input type=text name=username id=username /><br><label for=password> Password: </label><input type=password name=password id=register_password /><br><label for=Password_confirmation> password confirmation: </label><input type=password name=rpassword_confirmation id=password_confirmation /><br><input type=submit value=Register /></fieldset></form></td></tr></tbody></table></body></html>";
	}
}
