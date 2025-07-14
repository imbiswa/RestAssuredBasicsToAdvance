package Files;

import io.restassured.path.json.JsonPath;

public class reUsableMethods {
	
	
	public static JsonPath rawtojson(String response)
	{
		
		JsonPath sp = new JsonPath(response);
		return sp;
	}

}
