package api.gorest.tests;

import org.testng.annotations.Test;

import api.gorest.base.BaseTest;
import api.gorest.client.RestClient;
import api.gorest.pojo.UserRequest;
import api.gorest.utils.StringUtils;
import static org.hamcrest.Matchers.*;

public class CreateUser extends BaseTest {
	@Test
	public void createUserTest() {
		UserRequest usereq = new UserRequest("nancy", StringUtils.getRandomEmails(), "female", "active");
		// RestClient restclient1 = new RestClient();
		Integer userID = restclient.post("/public/v2/users", "json", usereq, true).then().log().all().assertThat()
				.statusCode(201).extract().path("id");
		System.out.println("User ID is:" + userID);
		// Get the same user
		RestClient restclient2 = new RestClient(prop, baseURI);
		restclient2.get("/public/v2/users/" + userID, true).then().log().all().assertThat().statusCode(200).assertThat()
				.body("id", equalTo(userID));
	}
}
