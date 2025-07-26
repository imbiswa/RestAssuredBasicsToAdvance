package POJO_Deserialization;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.util.List;

public class OAuthDemo {
	
	
	@Test
	public void Auth()
	{
		// TODO Auto-generated method stub

		String response =

		               given() 
		                   .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		                        .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		                        .formParams("grant_type", "client_credentials")
		                        .formParams("scope", "trust")
		                        .when().log().all()
		                        .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

		System.out.println(response);
		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		System.out.println(accessToken);
		    
		 AllCourses obj=  given()
		.queryParams("access_token", accessToken)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(AllCourses.class);
		//	System.out.println(getcourses);

		System.out.println(obj.getLinkedIn());
		
		//get the price of cypress
		List<WebAutomation> webAutomation = obj.getCourses().getWebAutomation();
		for(int i=0;i<=webAutomation.size()-1;i++)
		{
			if(webAutomation.get(i).getCourseTitle().equalsIgnoreCase("Cypress"))
			{
				System.out.println(webAutomation.get(i).getPrice());
			}
		}
		
		//get all cources of Mobile
		
		List<Mobile> mobile =obj.getCourses().getMobile();
		for(int i=0;i<=mobile.size()-1;i++)
		{
		   String Mprice=mobile.get(i).getPrice();
		   String Mcource=mobile.get(i).getCourseTitle();
		   System.out.println(Mcource+": "+Mprice);
		}
		
		
	
			
	}

}
