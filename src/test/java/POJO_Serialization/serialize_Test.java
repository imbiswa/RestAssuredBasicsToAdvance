package POJO_Serialization;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class serialize_Test {

	
	@Test
	public void addPlaceAPI()
	{
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		
		ArrayList<String> typesValues = new ArrayList<String>();
		typesValues.add("shoe park");
		typesValues.add("shop");
		p.setTypes(typesValues);
		
		Location l= new Location();
		l.setLat(38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		System.out.println(p);
		
		 RestAssured.baseURI = "https://rahulshettyacademy.com";
	     String response = given()
	    .log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	    .body(p)
	    .when().post("maps/api/place/add/json")
	    .then().log().all().statusCode(200).body("scope", equalTo("APP"))
	    .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	    System.out.println(response);	
	}
	
	
}
