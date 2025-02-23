package api.gorest.client;

import java.util.Map;
import java.util.Properties;

import api.gorest.frameworkexceptions.APIFrameworkExceptions;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private static RequestSpecBuilder specBuilder;
	private Properties prop;
	private String baseURI;
	private boolean isAuthorizationHeaderAdded = false;

	public RestClient(Properties prop, String baseURI) {
		specBuilder = new RequestSpecBuilder();
		this.prop = prop;
		this.baseURI = baseURI;
	}

	public void addAuthorizationHeader() {
		if(!isAuthorizationHeaderAdded)
		{
		specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("token"));
		isAuthorizationHeaderAdded = true;
		}
	}

	public void setRequestContentType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
		default:
			System.out.println("Please pass correct content type");
			throw new APIFrameworkExceptions("INVALID_CONTENT_TYPE");
		}
	}

	private RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMp) {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		if (headersMp != null) {
			specBuilder.addHeaders(headersMp);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMp, Map<String, String> querParams) {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		if (querParams != null) {
			specBuilder.addQueryParams(querParams);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType) {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType,
			Map<String, String> headersMap) {
		specBuilder.setBaseUri(baseURI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}

	// HTTP Mthods
	public Response get(String serviceURL, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec()).log().all().when().get(serviceURL);
		}
		return RestAssured.given(createRequestSpec()).when().get(serviceURL);
	}

	public Response get(String serviceURL, Map<String, String> headersMap, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(headersMap)).log().all().when().get(serviceURL);
		}
		return RestAssured.given(createRequestSpec(headersMap)).when().get(serviceURL);
	}

	public Response get(String serviceURL, Map<String, String> querParams, Map<String, String> headersMap,
			boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(headersMap, querParams)).log().all().when().get(serviceURL);
		}
		return RestAssured.given(createRequestSpec(headersMap, querParams)).when().get(serviceURL);
	}

	// Post Method
	public Response post(String serviceURL, String contentType, Object requestBody, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().post(serviceURL);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).post(serviceURL);
	}

	public Response post(String serviceURL, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
					.post(serviceURL);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).post(serviceURL);
	}

	// PUT MEthod
	public Response put(String serviceURL, String contentType, Object requestBody, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().put(serviceURL);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).put(serviceURL);
	}

	public Response put(String serviceURL, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
					.put(serviceURL);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).put(serviceURL);
	}

	// PATCH Method
	public Response patch(String serviceURL, String contentType, Object requestBody, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().patch(serviceURL);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).patch(serviceURL);
	}

	public Response patch(String serviceURL, String contentType, Object requestBody, Map<String, String> headersMap,
			boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
					.patch(serviceURL);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).patch(serviceURL);
	}

	// DELETE Method
	public Response delete(String serviceURL, boolean log) {
		if (log) {
			return RestAssured.given(createRequestSpec()).log().all().delete(serviceURL);
		}
		return RestAssured.given(createRequestSpec()).delete(serviceURL);
	}
}
