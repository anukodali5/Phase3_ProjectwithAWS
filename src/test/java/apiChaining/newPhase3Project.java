package apiChaining;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class newPhase3Project {

	Response response;

	String BaseURI = "http://44.196.48.98:8088/employees/";

	@Test
	public void test1() {

		response = GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);

		response = PostMethod("Test", "create", "2000", "abc@abc.com");
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath jpath = response.jsonPath();
		int Emp_Id = jpath.get("id");
		System.out.println("id:   " + Emp_Id);

		response = Putmethod(Emp_Id, "Rohit1", "Roy1", "3000", "abc1@abc.com");
		Assert.assertEquals(response.getStatusCode(), 200);
		jpath = response.jsonPath();
		Assert.assertEquals(jpath.get("firstName"), "Rohit1");

		response = DeleteMethod(Emp_Id);
		Assert.assertEquals(response.getStatusCode(), 200);
		// Assert.assertEquals(response.getBody().asString(), "");

		response = GetMethod(Emp_Id);
		Assert.assertEquals(response.getStatusCode(), 400);
		// Assert.assertEquals(response.getBody().asString(), "");
		jpath = response.jsonPath();
		Assert.assertEquals(jpath.get("message"), "Entity Not Found");

	}

	public Response GetMethodAll() {

		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		return response;

	}

	public Response PostMethod(String FName, String LName, String Salary, String email) {
		RestAssured.baseURI = BaseURI;
		JSONObject jobj = new JSONObject();
		jobj.put("firstName", FName);
		jobj.put("lastName", LName);
		jobj.put("salary", Salary);
		jobj.put("email", email);

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString()).post();
		return response;
	}

	public Response Putmethod(int EmpId, String FName, String LName, String Salary, String email) {
		RestAssured.baseURI = BaseURI;
		JSONObject jobj = new JSONObject();
		jobj.put("firstName", FName);
		jobj.put("lastName", LName);
		jobj.put("salary", Salary);
		jobj.put("email", email);

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString())
				.put("/" + EmpId);
		return response;

	}

	public Response DeleteMethod(int EmpId) {
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/" + EmpId);
		return response;
	}

	public Response GetMethod(int EmpId) {
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/" + EmpId);
		return response;
	}

}