package com.pixbrandtestcases;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pixbrand.base.TranquilleBase;
import com.pixbrand.report.Extent;
import com.pixbrand.utils.PropertyReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TranquilleTestcases extends TranquilleBase {

	public String BarierToken;
	public String VendorRegisterEndPointurl;
	static String baseUrl = PropertyReader.getConfigProperty("baseUrl");
	public static String EndPointUrl = PropertyReader.getConfigProperty("VendorRegisterEndPointurl");
	public static String EndPointUrl1 = PropertyReader.getConfigProperty("CountryEndPointurl");
	public static String EndPointUrl2 = PropertyReader.getConfigProperty("RegisterThreeEndPointurl");
	public static String RegisterPayload = PropertyReader.getDataProperty("RegisterPayload");
	public static String EndPointUrl3 = PropertyReader.getConfigProperty("VendorRegisterWorkDemo");
	public static String imageURL = PropertyReader.getDataProperty("ImageUrlDemo");
	public static String EndPointUrl4 = PropertyReader.getConfigProperty("VendorRegisterLogoEndPointUrl");
	public static String imageURLLogo = PropertyReader.getDataProperty("ImageUrlLogo");
	public static String EndPointUrl5 = PropertyReader.getConfigProperty("VendorRegisterLocationEndPointUrl");
	public static String RegisterLocationPayload = PropertyReader.getDataProperty("registerlocationPayload");
	public static String EndPointUrl6 = PropertyReader.getConfigProperty("VendorTretementEndPointUrl");
	public static String VendorTreatmentsPayload = PropertyReader.getDataProperty("TretementsPayload");

	public static String EndPointUrl7 = PropertyReader.getConfigProperty("LanguageEndPointUrl");

	public static String EndPointUrl8 = PropertyReader.getConfigProperty("ServicesEndPointUrl");

	public static String EndPointUrl9 = PropertyReader.getConfigProperty("ServicesPlanEndPointUrl");

	@Test(priority = 1)
	public void GetCountry() {
		RestAssured.baseURI = baseUrl;
		Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
		Extent.getTest().log(Status.PASS, "EndPointURL Is : " + EndPointUrl1);
		Response res = RestAssured.given().contentType(ContentType.JSON).when().get(EndPointUrl1).then().statusCode(200)
				.log().all().extract().response();

	}

	@Test(priority = 2)
	public void GetLanguage() {
		RestAssured.baseURI = baseUrl;
		Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
		Extent.getTest().log(Status.PASS, "EndPointURL Is : " + EndPointUrl7);
		Response res = RestAssured.given().contentType(ContentType.JSON).when().get(EndPointUrl7).then().statusCode(200)
				.log().all().extract().response();

	}

	@Test(priority = 3)
	public void GetServices() {
		RestAssured.baseURI = baseUrl;
		Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
		Extent.getTest().log(Status.PASS, "EndPointURL Is : " + EndPointUrl8);
		Response res = RestAssured.given().contentType(ContentType.JSON).when().get(EndPointUrl8).then().statusCode(200)
				.log().all().extract().response();

	}

	@Test(priority = 4)
	public void GetServicesPlan() {
		RestAssured.baseURI = baseUrl;
		Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
		Extent.getTest().log(Status.PASS, "EndPointURL Is : " + EndPointUrl9);
		Response res = RestAssured.given().contentType(ContentType.JSON).when().get(EndPointUrl9).then().statusCode(200)
				.log().all().extract().response();

	}

	@Test(priority = 4)
	public void vendorRegistration() {

		RestAssured.baseURI = baseUrl;
		Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
		Extent.getTest().log(Status.PASS, "EndPointURL Is : " + EndPointUrl);
		Response res = RestAssured.given().param("firstname", "Sunil").param("lastname", "Kumar")
				.param("email", "kabir78056@yopmail.com").param("password", "123456").param("phone", "9981521733")
				.param("firm_name", "vendor shop").param("website", "www.vendeorshop.commv").param("country", "india")
				.param("language", "english").param("vatnumber", "456697875214").when().post(EndPointUrl).then()
				.statusCode(200).log().all().extract().response();

		BarierToken = res.path("data.token");
		System.out.println("print token:" + BarierToken);

	}

	@Test(priority = 5)
	public void vendorregisterThree() {

		RestAssured.baseURI = baseUrl;
		Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
		Extent.getTest().log(Status.PASS, "EndPointURL Is : " + EndPointUrl2);
		Extent.getTest().log(Status.PASS, "JsonPayload Is : " + RegisterPayload);
		Response res = RestAssured.given().contentType(ContentType.JSON).auth().oauth2(BarierToken)
				.body(RegisterPayload).when().post(EndPointUrl2).then().statusCode(200).log().all().extract()
				.response();
	}

	@Test(priority = 6)
	public void vendorRegisterWorkDemo() {

		RestAssured.baseURI = baseUrl;
		Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
		Extent.getTest().log(Status.PASS, "EndPointURL Is : " + EndPointUrl3);
		Response res = RestAssured.given().contentType("multipart/form-data")
				.multiPart("demo_image[]", new File(imageURL), "multipart/form-data").auth().oauth2(BarierToken).when()
				.post(EndPointUrl3).then().statusCode(200).log().all().extract().response();
	}

	@Test(priority = 7)
	public void vendorRegisterLogo() {

		RestAssured.baseURI = baseUrl;
		Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
		Extent.getTest().log(Status.PASS, "EndPointURL Is : " + EndPointUrl4);
		Response res = RestAssured.given().contentType("multipart/form-data")
				.multiPart("logo", new File(imageURLLogo), "multipart/form-data").auth().oauth2(BarierToken).when()
				.post(EndPointUrl4).then().statusCode(200).log().all().extract().response();
	}

	@Test(priority = 8)
	public void vendorRegisterLocation() {

		RestAssured.baseURI = baseUrl;
		Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
		Extent.getTest().log(Status.PASS, "EndPointURL Is : " + EndPointUrl5);
		Extent.getTest().log(Status.PASS, "JsonPayload Is : " + RegisterLocationPayload);
		Response res = RestAssured.given().contentType(ContentType.JSON).auth().oauth2(BarierToken)
				.body(RegisterLocationPayload).when().post(EndPointUrl5).then().statusCode(200).log().all().extract()
				.response();

	}

	@Test(priority = 9)
	public void vendorTreatments() {

		RestAssured.baseURI = baseUrl;
		Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
		Extent.getTest().log(Status.PASS, "EndPointURL Is : " + EndPointUrl6);
		Extent.getTest().log(Status.PASS, "JsonPayload Is : " + VendorTreatmentsPayload);
		Response res = RestAssured.given().contentType(ContentType.JSON).auth().oauth2(BarierToken)
				.body(VendorTreatmentsPayload).when().post(EndPointUrl5).then()
				// .statusCode(200)
				.log().all().extract().response();

	}

}