package restAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {

	@Test

	public void test1() {
		RestAssured.given().baseUri("https://chercher.tech/sample/api/books.xml").when().get().then().log().body()
				.statusCode(200);
	}

	@Test

	public void test2() {
		Response response = RestAssured.given().baseUri("https://chercher.tech/sample/api/books.xml").when().get();
		// to retrieve book title
		NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
		// to retrieve book author
		// NodeChildrenImpl books =
		// response.then().extract().path("bookstore.book.author");
		System.out.println("All the books are" + books.toString());
		System.out.println("The first book is  " + books.get(0).toString());
		System.out.println("The second book is  " + books.get(1).toString());

		System.out.println("language of first book is   " + books.get(0).getAttribute("lang"));

		// to get all the book titles
		for (int i = 0; i < books.size(); i++) {
			System.out.println("book is " + books.get(i).toString());
		}
		// Navigating to child node
		NodeChildrenImpl prices = response.then().extract().path("bookstore.book.price");
		System.out.println("All the prices are " + prices.toString());
		// for good formatted output
		System.out.println("First price is " + prices.get(0).children().get("paperback"));

	}
}