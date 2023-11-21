package nevt;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import nevt.common.extensions.GuidGenerator;
import nevt.common.extensions.RandomStringGenerator;
import nevt.dto.account.SignInRequest;
import nevt.dto.account.SignUpRequest;
import nevt.dto.car.AttributeItemDTO;
import nevt.dto.car.AttributeTypeDTO;
import nevt.dto.car.CarDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class CarTest {
    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "api/cars";
    }

    @Test
    public void testCreateCar_Success() {
        String jwtToken = JwtUtils.generateEmployeeToken();
        Collection<AttributeTypeDTO> types = new ArrayList<>();
        List<AttributeItemDTO> items = new ArrayList<>();
        items.add(new AttributeItemDTO("red", 0));
        items.add(new AttributeItemDTO("purple", 550));
        List<AttributeItemDTO> items2 = new ArrayList<>();
        items2.add(new AttributeItemDTO("19 wheels", 345));
        items2.add(new AttributeItemDTO("21 wheels", 645));
        types.add(new AttributeTypeDTO("Paint", items));
        types.add(new AttributeTypeDTO("Wheels", items2));

        Collection<String> images = new ArrayList<>();
        images.add("616940_1.jpg");
        images.add("616940_2.jpg");
        images.add("616940_3.jpg");
        images.add("616940_4.jpg");
        images.add("616940_5.jpg");

        CarDTO carDTO = new CarDTO("", "Toyota Camry 2021", 59000D, "This is new model car", 2021, "Camry", "Toyota", 10, types, images);
        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(carDTO)
                .when().post("")
                .then()
                .statusCode(200)
                .and()
                .body("name", equalTo("Toyota Camry 2021"))
                .body("basePrice", equalTo(59000F))
                .body("description", equalTo("This is new model car"))
                .body("year", equalTo(2021))
                .body("model", equalTo("Camry"))
                .body("make", equalTo("Toyota"))
                .body("stockQuantity", equalTo(10))
                .body("attributeTypes.type", hasItems("Paint", "Wheels"))
                .body("attributeTypes.items[0].value", hasItems("red", "purple"))
                .body("attributeTypes.items[0].additionalPrice", hasItems(0F, 550F))
                .body("attributeTypes.items[1].value", hasItems("19 wheels", "21 wheels"))
                .body("attributeTypes.items[1].additionalPrice", hasItems(345F, 645F))
                .body("images", hasItems("616940_1.jpg", "616940_2.jpg", "616940_3.jpg", "616940_4.jpg", "616940_5.jpg"));
    }

    @Test
    public void testCreateCar_Fail_NonePermissionAccount() {
        String jwtToken = JwtUtils.generateUserToken();
        Collection<AttributeTypeDTO> types = new ArrayList<>();
        List<AttributeItemDTO> items = new ArrayList<>();
        items.add(new AttributeItemDTO("red", 0));
        items.add(new AttributeItemDTO("purple", 550));
        List<AttributeItemDTO> items2 = new ArrayList<>();
        items2.add(new AttributeItemDTO("19 wheels", 345));
        items2.add(new AttributeItemDTO("21 wheels", 645));
        types.add(new AttributeTypeDTO("Paint", items));
        types.add(new AttributeTypeDTO("Wheels", items2));

        Collection<String> images = new ArrayList<>();
        images.add("616940_1.jpg");
        images.add("616940_2.jpg");
        images.add("616940_3.jpg");
        images.add("616940_4.jpg");
        images.add("616940_5.jpg");

        CarDTO carDTO = new CarDTO("", "Toyota Camry 2021", 59000D, "This is new model car", 2021, "Camry", "Toyota", 10, types, images);
        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(carDTO)
                .when().post("")
                .then()
                .statusCode(403)
                .and()
                .body("status", equalTo(403))
                .body("error", equalTo("Forbidden"));
    }

    @Test
    public void testCreateCar_Fail_InvalidInput() {
        String jwtToken = JwtUtils.generateUserToken();

        Collection<String> images = new ArrayList<>();
        images.add("616940_1.jpg");
        images.add("616940_2.jpg");
        images.add("616940_3.jpg");
        images.add("616940_4.jpg");
        images.add("616940_5.jpg");

        CarDTO carDTO = new CarDTO("", "T", null, "This is new model car", 2021, "Camry", "Toyota", 10, null, images);
        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(carDTO)
                .when().post("")
                .then()
                .statusCode(400)
                .and()
                .body("data", is(nullValue()))
                .body("fieldError.attributeTypes", equalTo("must not be empty"))
                .body("fieldError.name", equalTo("size must be between 2 and 100"))
                .body("fieldError.basePrice", equalTo("must not be null"))
                .body("status", equalTo("BAD_REQUEST"));
        ;
    }

    @Test
    public void testGetCar_Success() {
        String jwtToken = JwtUtils.generateEmployeeToken();
        Collection<AttributeTypeDTO> types = new ArrayList<>();
        List<AttributeItemDTO> items = new ArrayList<>();
        items.add(new AttributeItemDTO("red", 0));
        items.add(new AttributeItemDTO("purple", 550));
        List<AttributeItemDTO> items2 = new ArrayList<>();
        items2.add(new AttributeItemDTO("19 wheels", 345));
        items2.add(new AttributeItemDTO("21 wheels", 645));
        types.add(new AttributeTypeDTO("Paint", items));
        types.add(new AttributeTypeDTO("Wheels", items2));

        Collection<String> images = new ArrayList<>();
        images.add("616940_1.jpg");
        images.add("616940_2.jpg");
        images.add("616940_3.jpg");
        images.add("616940_4.jpg");
        images.add("616940_5.jpg");

        CarDTO carDTO = new CarDTO("", "Toyota Camry 2021", 59000D, "This is new model car", 2021, "Camry", "Toyota", 10, types, images);
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(carDTO)
                .when().post("");

        String carId = response.jsonPath().getString("productNumber");

        given().contentType("application/json")
                .when().get(carId)
                .then()
                .statusCode(200)
                .and()
                .body("name", equalTo("Toyota Camry 2021"))
                .body("basePrice", equalTo(59000F))
                .body("description", equalTo("This is new model car"))
                .body("year", equalTo(2021))
                .body("model", equalTo("Camry"))
                .body("make", equalTo("Toyota"))
                .body("stockQuantity", equalTo(10))
                .body("attributeTypes.type", hasItems("Paint", "Wheels"))
                .body("attributeTypes.items[0].value", hasItems("red", "purple"))
                .body("attributeTypes.items[0].additionalPrice", hasItems(0F, 550F))
                .body("attributeTypes.items[1].value", hasItems("19 wheels", "21 wheels"))
                .body("attributeTypes.items[1].additionalPrice", hasItems(345F, 645F))
                .body("images", hasItems("616940_1.jpg", "616940_2.jpg", "616940_3.jpg", "616940_4.jpg", "616940_5.jpg"));
    }

    @Test
    public void testGetCar_Fail_NotExistedProductNumber() {
        String randomProductNumber = GuidGenerator.generateGuid();

        Response response = given().contentType("application/json")
                .when().get(randomProductNumber);
        Object abc = response.jsonPath();
        given().contentType("application/json")
                .when().get(randomProductNumber)
                .then()
                .statusCode(404)
                .and()
                .body("errorMessage", equalTo("Car with product Number = "+randomProductNumber+" is not available"));
    }

    @Test
    public void testUpdateCar_Success() {
        String jwtToken = JwtUtils.generateEmployeeToken();
        String jwtUserToken = JwtUtils.generateUserToken();

        Collection<AttributeTypeDTO> types = new ArrayList<>();
        List<AttributeItemDTO> items = new ArrayList<>();
        items.add(new AttributeItemDTO("red", 0));
        items.add(new AttributeItemDTO("purple", 550));
        List<AttributeItemDTO> items2 = new ArrayList<>();
        items2.add(new AttributeItemDTO("19 wheels", 345));
        items2.add(new AttributeItemDTO("21 wheels", 645));
        types.add(new AttributeTypeDTO("Paint", items));
        types.add(new AttributeTypeDTO("Wheels", items2));

        Collection<String> images = new ArrayList<>();
        images.add("616940_1.jpg");
        images.add("616940_2.jpg");
        images.add("616940_3.jpg");
        images.add("616940_4.jpg");
        images.add("616940_5.jpg");

        CarDTO carDTO = new CarDTO("", "Toyota Camry 2021", 59000D, "This is new model car", 2021, "Camry", "Toyota", 10, types, images);
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(carDTO)
                .when().post("");

        String carId = response.jsonPath().getString("productNumber");

        carDTO = new CarDTO(carId, "Toyota Camry 2022", 49000D, "This is newer model car", 2022, "Camry", "Toyota", 10, types, images);
        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(carDTO)
                .when().put("/" + carId)
                .then()
                .statusCode(200);

        given().contentType("application/json")
                .when().get(carId)
                .then()
                .statusCode(200)
                .and()
                .body("name", equalTo("Toyota Camry 2022"))
                .body("basePrice", equalTo(49000F))
                .body("description", equalTo("This is newer model car"))
                .body("year", equalTo(2022))
                .body("model", equalTo("Camry"))
                .body("make", equalTo("Toyota"))
                .body("stockQuantity", equalTo(10))
                .body("attributeTypes.type", hasItems("Paint", "Wheels"))
                .body("attributeTypes.items[0].value", hasItems("red", "purple"))
                .body("attributeTypes.items[0].additionalPrice", hasItems(0F, 550F))
                .body("attributeTypes.items[1].value", hasItems("19 wheels", "21 wheels"))
                .body("attributeTypes.items[1].additionalPrice", hasItems(345F, 645F))
                .body("images", hasItems("616940_1.jpg", "616940_2.jpg", "616940_3.jpg", "616940_4.jpg", "616940_5.jpg"));
    }

    @Test
    public void testDeleteCar_Success() {
        String jwtToken = JwtUtils.generateEmployeeToken();

        Collection<AttributeTypeDTO> types = new ArrayList<>();
        List<AttributeItemDTO> items = new ArrayList<>();
        items.add(new AttributeItemDTO("red", 0));
        items.add(new AttributeItemDTO("purple", 550));
        List<AttributeItemDTO> items2 = new ArrayList<>();
        items2.add(new AttributeItemDTO("19 wheels", 345));
        items2.add(new AttributeItemDTO("21 wheels", 645));
        types.add(new AttributeTypeDTO("Paint", items));
        types.add(new AttributeTypeDTO("Wheels", items2));

        Collection<String> images = new ArrayList<>();
        images.add("616940_1.jpg");
        images.add("616940_2.jpg");
        images.add("616940_3.jpg");
        images.add("616940_4.jpg");
        images.add("616940_5.jpg");

        CarDTO carDTO = new CarDTO("", "Toyota Camry 2021", 59000D, "This is new model car", 2021, "Camry", "Toyota", 10, types, images);
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(carDTO)
                .when().post("");

        String carId = response.jsonPath().getString("productNumber");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .when().delete("/" + carId)
                .then()
                .statusCode(204);

        given().contentType("application/json")
                .when().get(carId)
                .then()
                .statusCode(404)
                .and()
                .body("errorMessage", equalTo("Car with product Number = " + carId + " is not available"));

    }
}