package api.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.gorest.base.BaseTest;
import api.gorest.client.RestClient;
import api.gorest.constants.APIHTTPStatus;
import api.gorest.pojo.UserRequest;
import api.gorest.utils.StringUtils;
import static org.hamcrest.Matchers.*;

public class CreateUser extends BaseTest {

	@BeforeMethod
	public void createUserSetup() {
		restclient = new RestClient(prop, baseURI);
	}

	@Test
	public void createUserTest() {
		UserRequest usereq = new UserRequest("nancy", StringUtils.getRandomEmails(), "female", "active");
		Integer userID = restclient.post(GOREST_ENDPOINT, "json", usereq, true).then().log().all().assertThat()
				.statusCode(APIHTTPStatus.CREATED_201.getCode()).extract().path("id");
		System.out.println("User ID is:" + userID);
		// Get the same user
		RestClient restclient2 = new RestClient(prop, baseURI);
		restclient2.get(GOREST_ENDPOINT + "/" + userID, true).then().log().all().assertThat().statusCode(200)
				.assertThat().body("id", equalTo(userID));
	}
}
