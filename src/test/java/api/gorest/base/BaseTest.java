package api.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import api.gorest.client.RestClient;
import api.gorest.configuration.ConfigManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	//Service URL's
	public static final String GOREST_ENDPOINT = "public/v2/users";

	protected ConfigManager config;
	protected Properties prop;
	protected RestClient restclient;
	protected String baseURI;

	@Parameters({ "baseURI" })
	@BeforeTest
	public void setUp(String baseURI) {
		RestAssured.filters(new AllureRestAssured());
		config = new ConfigManager();
		prop = config.initProp();
		this.baseURI = baseURI;
		//restclient = new RestClient(prop, baseURI);
	}
}
