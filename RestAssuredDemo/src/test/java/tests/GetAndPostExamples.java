package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;


public class GetAndPostExamples {
	@Test
	public void testGet() {
		
		baseURI = "https://reqres.in/api";
		Response response =given().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data[4].first_name",equalTo("George")).
			//body("data.first_name", hasItems("George","Rachel").Just adding few words
			extract().
			response();
		String name = response.path("data[4].first_name");
		System.out.println("First name: "+ name);
	}
	
	@Test
	public void testPost() {
	
      //Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name", "Desi");
//		map.put("job", "Sr QA Engneer");
//		System.out.println(map);
//		
		JSONObject request = new JSONObject();
		request.put("name", "Desi");
		request.put("job", "Sr QA Engineer");
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
	

}
