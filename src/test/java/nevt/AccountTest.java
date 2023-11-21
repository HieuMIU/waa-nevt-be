package nevt;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import nevt.common.extensions.RandomStringGenerator;
import nevt.dto.account.SignInRequest;
import nevt.dto.account.SignUpRequest;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class AccountTest {
    @BeforeClass
    public static void setup(){
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "auth";
    }

    @Test
    public void testSignUp_Success() {
        String randomEmail = RandomStringGenerator.generateRandomString(7) + "@gmail.com";
        String randomPassword = RandomStringGenerator.generateRandomString(7);
        SignUpRequest signUpRequest = new SignUpRequest("John","Senior",randomEmail, randomPassword);

        given().contentType("application/json")
                .body(signUpRequest)
                .when().post("signup")
                .then()
                .statusCode(200)
                .and()
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Senior"))
                .body("email", equalTo(randomEmail))
                .body("role", equalTo("ROLE_USER"));
    }

    @Test
    public void testSignup_Fail_TooShortPassword() {
        String randomEmail = RandomStringGenerator.generateRandomString(7) + "@gmail.com";
        String randomPassword = RandomStringGenerator.generateRandomString(5);
        SignUpRequest signUpRequest = new SignUpRequest("John","Senior",randomEmail, randomPassword);

        given().contentType("application/json")
                .body(signUpRequest)
                .when().post("signup")
                .then()
                .statusCode(400)
                .and()
                .body("data", is(nullValue()))
                .body("fieldError.password", equalTo("size must be between 6 and 50"))
                .body("isSuccess", equalTo(false))
                .body("status", equalTo("BAD_REQUEST"));
    }

    @Test
    public void testSignup_Fail_NullEmail() {
        String randomPassword = RandomStringGenerator.generateRandomString(5);
        SignUpRequest signUpRequest = new SignUpRequest("John","Senior",null, randomPassword);

        given().contentType("application/json")
                .body(signUpRequest)
                .when().post("signup")
                .then()
                .statusCode(400)
                .and()
                .body("data", is(nullValue()))
                .body("fieldError.email", equalTo("must not be empty"))
                .body("isSuccess", equalTo(false))
                .body("status", equalTo("BAD_REQUEST"));
    }

    @Test
    public void testLogin_Success() {
        String randomEmail = RandomStringGenerator.generateRandomString(7) + "@gmail.com";
        String randomPassword = RandomStringGenerator.generateRandomString(7);
        SignUpRequest signUpRequest = new SignUpRequest("John","Senior",randomEmail, randomPassword);
        SignInRequest signInRequest = new SignInRequest(randomEmail, randomPassword);
        given().contentType("application/json")
                .body(signUpRequest)
                .when().post("signup")
                .then()
                .statusCode(200)
                .and()
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Senior"))
                .body("email", equalTo(randomEmail))
                .body("role", equalTo("ROLE_USER"));

        given().contentType("application/json")
                .body(signInRequest)
                .when().post("signin")
                .then()
                .statusCode(200)
                .and()
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Senior"))
                .body("email", equalTo(randomEmail))
                .body("role", equalTo("ROLE_USER"));
    }

    @Test
    public void testLogin_Fail_WrongPassword() {
        String randomEmail = RandomStringGenerator.generateRandomString(7) + "@gmail.com";
        String randomPassword = RandomStringGenerator.generateRandomString(7);
        String otherRandomPassword = RandomStringGenerator.generateRandomString(7);
        SignUpRequest signUpRequest = new SignUpRequest("John","Senior",randomEmail, randomPassword);
        SignInRequest signInRequest = new SignInRequest(randomEmail, otherRandomPassword);
        given().contentType("application/json")
                .body(signUpRequest)
                .when().post("signup")
                .then()
                .statusCode(200)
                .and()
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Senior"))
                .body("email", equalTo(randomEmail))
                .body("role", equalTo("ROLE_USER"));

        given().contentType("application/json")
                .body(signInRequest)
                .when().post("signin")
                .then()
                .statusCode(403)
                .and()
                .body("error", equalTo("Forbidden"))
                .body("status", equalTo(403));
    }
}
