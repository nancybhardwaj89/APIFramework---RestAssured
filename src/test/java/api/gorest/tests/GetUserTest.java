package api.gorest.tests;

import org.testng.annotations.Test;
import api.gorest.client.RestClient;
import static org.hamcrest.Matchers.*;

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
		restclient.get("/public/v2/users/6992070", false).then().log().all().assertThat().statusCode(200).and()
				.body("id", equalTo(6992070));
	}
}
