package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class XMLSchemaValidation {
	
	@Test
	public void schemaValidation() {
		File file = new File("SoapRequest/Add.xml");

		if (file.exists())
			System.out.println("File exists");

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String requestBody = null;
		try {
			requestBody = IOUtils.toString(fileInputStream, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		baseURI = "http://www.dneonline.com/";

		given().contentType("text/xml").accept(ContentType.XML).body(requestBody).when().post("/calculator.asmx").then()
				.statusCode(200).log().all().and().body("//*:AddResult.text()", equalTo("5")).
				and().assertThat().body(matchesXsdInClasspath("Calculator.xsd"));
	}

}
