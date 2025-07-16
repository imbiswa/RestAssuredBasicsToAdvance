package Files;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class DynamicJsons {

    List<String> deleteIds = new ArrayList<>();
   
    @Test(dataProvider = "Booksdata")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";

        String response = given().log().all()
            .header("Content-Type", "application/json")
            .body(Payload.Addbook(isbn, aisle))
        .when().post("/Library/Addbook.php")
        .then().log().all().assertThat().statusCode(200)
        .extract().response().asString();

        JsonPath jp = reUsableMethods.rawtojson(response);
        String id = jp.getString("ID");
        deleteIds.add(id);
        System.out.println("Book added with ID: " + id);
    }
    
    
    @Test(dependsOnMethods = {"addBook"})
    public void deleteBooks() {
        RestAssured.baseURI = "http://216.10.245.166";

        for (String id : deleteIds) {
            String dresponse = given().log().all()
                .header("Content-Type", "application/json")
                .body("{\"ID\":\"" + id + "\"}")
            .when().post("/Library/DeleteBook.php")
            .then().log().all().assertThat().statusCode(200)
            .extract().response().asString();
            JsonPath jp = reUsableMethods.rawtojson(dresponse);
            String ActualMsg = "book is successfully deleted";
            String message=jp.get("msg");
            Assert.assertEquals(ActualMsg , message);
            
            System.out.println("Book deleted with ID: " + id);
        }
    }

    @DataProvider(name = "Booksdata")
    public Object[][] bookData() {
        return new Object[][] {
            {"AAAA12", "000012"},
            {"BBBB22", "111112"},
            {"CCCC33", "222212"}
        };
    }
}