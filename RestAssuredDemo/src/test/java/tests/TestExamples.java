package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class TestExamples {

	@Test
	public void test_getSimpleResponse() {
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeader("content-type"));
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);

	}
	
	@Test
	public void test_staticImport() {
		baseURI = "https://reqres.in/api";
	Response response =given().
		get("/users?page=2").
	then().
		statusCode(200).
		body("data[1].id",equalTo(8)).
		body("total", equalTo(12)).
		body("data[1].email",equalTo("lindsay.ferguson@reqres.in")).
		extract().
		response();
	
	String email = response.path("data[1].email");
	System.out.println("email: "+ email);
	
		
	
	}

	
	}

