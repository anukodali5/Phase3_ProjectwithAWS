package restAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithParams {
	@Test
	public void test1() {

		RestAssured.baseURI = "http://localhost:3000/employees/";
		RequestSpecification request = RestAssured.given();
		Response response = request.param("id", 1).get();
		// Assert.assertEquals(response,"Pankaj");
		String Responsebody = response.getBody().asString();
		System.out.println(Responsebody);
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
		// not a perfect way of validation instead will be using Json
		Assert.assertTrue(Responsebody.contains("Pankaj"));
		JsonPath jpath = response.jsonPath();
		List<String> names = jpath.get("name");
		Assert.assertEquals(names.get(0), "Pankaj");
		// List<String> names = japth.get("name");

		// System.out.println(names);
		String Header = response.getHeader("Content-Type");
		System.out.println(Header);

	}

}
