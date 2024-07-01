package api.gorest.tests;

import org.testng.annotations.Test;
import api.gorest.client.RestClient;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest {
	RestClient restclient;

	@Test
	public void getAllUsersTest() {
		restclient = new RestClient();
		restclient.get("/public/v2/users", true).then().log().all().assertThat().statusCode(200);
	}
	@Test
	public void getParticularUserTest() {
		restclient = new RestClient();
		restclient.get("/public/v2/users/6991573", false).then().log().all().assertThat().statusCode(200).and()
				.body("id", equalTo(6991573));
	}
	@Test
	public void getUserTestwithQueryParams() {
		restclient = new RestClient();
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("name", "nancy");
		queryParams.put("status", "active");
		restclient.get("/public/v2/users/", queryParams, null, true).then().log().all().assertThat().statusCode(200);
	}
}
