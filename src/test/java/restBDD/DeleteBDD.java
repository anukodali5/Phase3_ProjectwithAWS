package restBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteBDD {

	@Test
	public void test1() {
		RestAssured.given().baseUri("http://localhost:3000/employees/").when().delete("/35").then().log().body()
				.statusCode(200);

	}

}
