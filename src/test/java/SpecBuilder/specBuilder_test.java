package SpecBuilder;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;

import org.testng.annotations.Test;

import POJO_Serialization.AddPlace;
import POJO_Serialization.Location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class specBuilder_test {
	
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
		
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
	    ResponseSpecification res =	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification request = given().spec(req).body(p);
		
	    Response response=request.when().post("maps/api/place/add/json").then().spec(res).extract().response();
	    String stringAsString=response.asString();
	    
	    System.out.println(stringAsString);	
	}
	

}
