package api.gorest.tests;

import org.testng.annotations.Test;

import api.gorest.base.BaseTest;
import api.gorest.client.RestClient;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest {

	@Test
	public void getAllUsersTest() {
		restclient.get("/public/v2/users", true).then().log().all().assertThat().statusCode(200);
	}

	@Test(enabled=true)
	public void getParticularUserTest() {
		restclient = new RestClient(prop, baseURI);
		restclient.get("/public/v2/users/7004555", false).then().log().all().assertThat().statusCode(200).and()
				.body("id", equalTo(7004555));
	}

	@Test (enabled=true)
	public void getUserTestwithQueryParams() {
		restclient = new RestClient(prop, baseURI);
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("name", "nancy");
		queryParams.put("status", "active");
		restclient.get("/public/v2/users/", queryParams, null, true).then().log().all().assertThat().statusCode(200);
	}
}
