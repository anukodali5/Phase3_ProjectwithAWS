package restAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithMap {

	@Test
	public void test1() {

		RestAssured.baseURI = "http://localhost:3000/employees/";
		Map<String, Object> MapObj = new HashMap<String, Object>();
		MapObj.put("name", "Aproova");
		MapObj.put("salary", "6000");

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(MapObj).post("/create");

		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);

		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 201);

		JsonPath jpath = response.jsonPath();
		System.out.println("id:   " + jpath.get("id"));

	}
}
