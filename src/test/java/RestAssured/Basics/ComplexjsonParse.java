package RestAssured.Basics;

import static org.testng.Assert.assertEquals;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexjsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath (Payload.CoursePrice());	
		//1. Print No of courses returned by API
		int coursecount=js.getInt("courses.size()");
		System.out.println(coursecount);
		
		//2.Print Purchase Amount
		int totalAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//3. Print Title of the first course
		String firsttitle=js.get("courses[0].title");
		System.out.println(firsttitle);
		
		//4. Print All course titles and their respective Prices
		
		for(int i=0;i<coursecount;i++)
		{
			String coursettile=js.get("courses["+i+"].title");
			int coursePrice=js.getInt("courses["+i+"].price");
			System.out.println(coursettile +":"+coursePrice);
		}
		//5. Print no of copies sold by RPA Course
		for(int i=0;i<coursecount;i++)
		{
			String coursetitle=js.get("courses["+i+"].title");
			if (coursetitle.equals("RPA"))
			{
				int RPAcopies=js.getInt("courses["+i+"].copies");
				System.out.println(RPAcopies);
				break;
			}
		}
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		int priceSum =0;
		for(int i=0;i<coursecount;i++)
		{
			
			int coursePrice=js.getInt("courses["+i+"].price");
			priceSum=coursePrice+priceSum;
			
		}
		assertEquals(totalAmount, priceSum);

	}

}
