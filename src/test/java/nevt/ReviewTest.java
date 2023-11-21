package nevt;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import nevt.common.constants.OrderStatus;
import nevt.common.extensions.GuidGenerator;
import nevt.dto.car.AttributeItemDTO;
import nevt.dto.car.AttributeTypeDTO;
import nevt.dto.car.CarDTO;
import nevt.dto.order.AddressDTO;
import nevt.dto.order.CardDTO;
import nevt.dto.order.OrderDTO;
import nevt.dto.order.OrderItemDTO;
import nevt.dto.review.ReviewDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class ReviewTest {
    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "api";
    }

    @Test
    public void CreateReviewSuccess(){
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
                .when().post("cars");

        String carId = response.jsonPath().getString("productNumber");

        CardDTO cardDTO = new CardDTO("VISA", "0123456789", "08/29", "345");
        AddressDTO addressDTO = new AddressDTO("John Smith", "johnsmith@gmail.com", "641233112", "103 Main St", "FairField", 52556);

        response = given().contentType("application/json")
                .when().get("cars/" + carId);
        carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", orderItems, "johnsmith@gmail.com", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

        response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(orderDTO)
                .when().post("orders");

        String orderId = response.jsonPath().getString("orderId");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderId)
                .when().post("orders/manage/ship");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderId)
                .when().post("orders/manage/deliver");

        ReviewDTO reviewDTO = new ReviewDTO("", "johnsmith@gmail.com", carId, "This product is so good!");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(reviewDTO)
                .when().post("reviews")
                .then()
                .statusCode(200)
                .and()
                .body("email",equalTo("johnsmith@gmail.com"))
                .body("productNumber",equalTo(carId))
                .body("comment", equalTo("This product is so good!"));


    }

    @Test
    public void CreateReview_Fail_NotDeliveriedOrder(){
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
                .when().post("cars");

        String carId = response.jsonPath().getString("productNumber");

        CardDTO cardDTO = new CardDTO("VISA", "0123456789", "08/29", "345");
        AddressDTO addressDTO = new AddressDTO("John Smith", "johnsmith@gmail.com", "641233112", "103 Main St", "FairField", 52556);

        response = given().contentType("application/json")
                .when().get("cars/" + carId);
        carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", orderItems, "johnsmith@gmail.com", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

        response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(orderDTO)
                .when().post("orders");

        String orderId = response.jsonPath().getString("orderId");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderId)
                .when().post("orders/manage/ship");

        ReviewDTO reviewDTO = new ReviewDTO("", "johnsmith@gmail.com", carId, "This product is so good!");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(reviewDTO)
                .when().post("reviews")
                .then()
                .statusCode(500)
                .and()
                .body("errorMessage", equalTo("User have not ordered this car yet/ Your order does not delivery."));
    }

    @Test
    public void GetReview_Success(){
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
                .when().post("cars");

        String carId = response.jsonPath().getString("productNumber");

        CardDTO cardDTO = new CardDTO("VISA", "0123456789", "08/29", "345");
        AddressDTO addressDTO = new AddressDTO("John Smith", "johnsmith@gmail.com", "641233112", "103 Main St", "FairField", 52556);

        response = given().contentType("application/json")
                .when().get("cars/" + carId);
        carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", orderItems, "johnsmith@gmail.com", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

        response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(orderDTO)
                .when().post("orders");

        String orderId = response.jsonPath().getString("orderId");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderId)
                .when().post("orders/manage/ship");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderId)
                .when().post("orders/manage/deliver");

        ReviewDTO reviewDTO = new ReviewDTO("", "johnsmith@gmail.com", carId, "This product is so good!");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(reviewDTO)
                .when().post("reviews");

        given().contentType("application/json")
                .when().get("reviews/" + carId + "/johnsmith@gmail.com")
                .then()
                .statusCode(200)
                .and()
                .body("email",equalTo("johnsmith@gmail.com"))
                .body("productNumber",equalTo(carId))
                .body("comment", equalTo("This product is so good!"));
    }

    @Test
    public void GetReviewsList_Success(){
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
                .when().post("cars");

        String carId = response.jsonPath().getString("productNumber");

        CardDTO cardDTO = new CardDTO("VISA", "0123456789", "08/29", "345");
        AddressDTO addressDTO = new AddressDTO("John Smith", "johnsmith@gmail.com", "641233112", "103 Main St", "FairField", 52556);

        response = given().contentType("application/json")
                .when().get("cars/" + carId);
        carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", orderItems, "johnsmith@gmail.com", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

        response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(orderDTO)
                .when().post("orders");

        String orderId = response.jsonPath().getString("orderId");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderId)
                .when().post("orders/manage/ship");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderId)
                .when().post("orders/manage/deliver");

        ReviewDTO reviewDTO = new ReviewDTO("", "johnsmith@gmail.com", carId, "This product is so good!");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(reviewDTO)
                .when().post("reviews");

        given().contentType("application/json")
                .when().get("reviews/" + carId)
                .then()
                .statusCode(200)
                .and()
                .body("email",hasItem("johnsmith@gmail.com"))
                .body("productNumber",hasItem(carId))
                .body("comment", hasItem("This product is so good!"));
    }
}