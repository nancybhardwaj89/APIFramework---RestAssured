package api.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.gorest.base.BaseTest;
import api.gorest.client.RestClient;
import api.gorest.constants.APIHTTPStatus;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest {

	@BeforeMethod
	public void getUserSetup() {
		restclient = new RestClient(prop, baseURI);
	}

	@Test
	public void getAllUsersTest() {
		restclient.get(GOREST_ENDPOINT, true).then().log().all().assertThat()
				.statusCode(APIHTTPStatus.OK_200.getCode());
	}

	@Test(enabled = true)
	public void getParticularUserTest() {
		restclient.get(GOREST_ENDPOINT + "/" + 7012823, false).then().log().all().assertThat()
				.statusCode(APIHTTPStatus.OK_200.getCode()).and().body("id", equalTo(7012823));
	}

	@Test(enabled = true)
	public void getUserTestwithQueryParams() {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("name", "nancy");
		queryParams.put("status", "active");
		restclient.get(GOREST_ENDPOINT, queryParams, null, true).then().log().all().assertThat()
				.statusCode(APIHTTPStatus.OK_200.getCode());
	}
}
