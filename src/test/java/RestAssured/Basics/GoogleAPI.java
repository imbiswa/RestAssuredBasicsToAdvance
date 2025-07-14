package RestAssured.Basics;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import Files.Payload;
import Files.reUsableMethods;
public class GoogleAPI {
	
	
	public static void main(String[] args) {
		
		//Add Place
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	     String response = given()
	    .log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	    .body(Payload.AddPlace())
	    .when().post("maps/api/place/add/json")
	    .then().log().all().statusCode(200).body("scope", equalTo("APP"))
	    .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	    System.out.println(response);
	    
	    JsonPath js = new JsonPath(response);
	    String placeid=js.getString("place_id");
	    System.out.println(placeid);
	    
	    
	    //update API
	    
	    String updateaddresss="70 Summer walk, USA";
	    given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	    .body("{\r\n"
	    		+ "\"place_id\":\""+placeid+"\",\r\n"
	    		+ "\"address\":\""+updateaddresss+"\",\r\n"
	    		+ "\"key\":\"qaclick123\"\r\n"
	    		+ "}")
	    .when().put("maps/api/place/update/json").then().statusCode(200).body("msg", equalTo("Address successfully updated"));
	    
	    
	    //Get Place
	    
	 String getplaceresponse=   given().log().all().queryParam("key", "qaclick123")
	    .queryParam("place_id", placeid)
	    .when().get("maps/api/place/get/json")
	    .then().log().all().statusCode(200).extract().response().asString();
	 
	JsonPath sp = reUsableMethods.rawtojson(getplaceresponse);
	String actualaddress= sp.getString("address");
	System.out.println(actualaddress);
	Assert.assertEquals(actualaddress, updateaddresss);
	 
	 
	   
	
		
	}
}
