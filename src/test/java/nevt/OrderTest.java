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
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class OrderTest {
    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "api";
    }

    @Test
    public void CreateOrder_Success() {
        String jwtToken = JwtUtils.generateUserToken();

        CardDTO cardDTO = new CardDTO("VISA","0123456789", "08/29","345");
        AddressDTO addressDTO = new AddressDTO("Dinh Thang Nguyen","dnguyen@miu.edu","641233112", "103 Main St", "FairField", 52556);

        Response response = given().contentType("application/json")
                .when().get("cars/b8f29c31-6f79-4cc4-b939-f5048d4a4f3c");
        CarDTO carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", items, "dnguyen@miu.edu", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderDTO)
                .when().post("orders")
                .then()
                .statusCode(200)
                .and()
                .body("email", equalTo("dnguyen@miu.edu"))
                .body("orderStatus", equalTo("PLACED"))
                .body("total", equalTo(99990F))
                .body("card.type", equalTo("VISA"))
                .body("card.number", equalTo("0123456789"))
                .body("address.name", equalTo("Dinh Thang Nguyen"))
                .body("address.phone", equalTo("641233112"));
    }

    @Test
    public void CreateOrder_Fail_NullCard() {
        String jwtToken = JwtUtils.generateUserToken();

        AddressDTO addressDTO = new AddressDTO("Dinh Thang Nguyen","dnguyen@miu.edu","641233112", "103 Main St", "FairField", 52556);

        Response response = given().contentType("application/json")
                .when().get("cars/b8f29c31-6f79-4cc4-b939-f5048d4a4f3c");
        CarDTO carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", items, "dnguyen@miu.edu", OrderStatus.PLACED, LocalDate.now(), 99990, null, addressDTO);

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderDTO)
                .when().post("orders")
                .then()
                .statusCode(400)
                .and()
                .body("data", is(nullValue()))
                .body("fieldError.card", equalTo("must not be null"))
                .body("status", equalTo("BAD_REQUEST"));
    }

    @Test
    public void ManageOrder_Success() {
        String jwtToken = JwtUtils.generateEmployeeToken();
        String jwtUserToken = JwtUtils.generateUserToken();
        CardDTO cardDTO = new CardDTO("VISA", "0123456789", "08/29", "345");
        AddressDTO addressDTO = new AddressDTO("Dinh Thang Nguyen", "dnguyen@miu.edu", "641233112", "103 Main St", "FairField", 52556);

        Response response = given().contentType("application/json")
                .when().get("cars/b8f29c31-6f79-4cc4-b939-f5048d4a4f3c");
        CarDTO carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", items, "dnguyen@miu.edu", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

        response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(orderDTO)
                .when().post("orders");

        String orderId = response.jsonPath().getString("orderId");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .when().get("orders/manage")
                .then()
                .statusCode(200)
                .and()
                .body("orderId", hasItem(orderId));
    }

    @Test
    public void ShipOrder_Success() {
        String jwtToken = JwtUtils.generateEmployeeToken();
        String jwtUserToken = JwtUtils.generateUserToken();
        CardDTO cardDTO = new CardDTO("VISA", "0123456789", "08/29", "345");
        AddressDTO addressDTO = new AddressDTO("Dinh Thang Nguyen", "dnguyen@miu.edu", "641233112", "103 Main St", "FairField", 52556);

        Response response = given().contentType("application/json")
                .when().get("cars/b8f29c31-6f79-4cc4-b939-f5048d4a4f3c");
        CarDTO carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", items, "dnguyen@miu.edu", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

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
                .when().get("orders/manage?orderStatus=SHIPPED")
                .then()
                .statusCode(200)
                .and()
                .body("orderId", hasItem(orderId));
    }

    @Test
    public void ShipOrder_Fail_NonePermissionAccount() {
        String jwtToken = JwtUtils.generateEmployeeToken();
        String jwtUserToken = JwtUtils.generateUserToken();
        CardDTO cardDTO = new CardDTO("VISA", "0123456789", "08/29", "345");
        AddressDTO addressDTO = new AddressDTO("Dinh Thang Nguyen", "dnguyen@miu.edu", "641233112", "103 Main St", "FairField", 52556);

        Response response = given().contentType("application/json")
                .when().get("cars/b8f29c31-6f79-4cc4-b939-f5048d4a4f3c");
        CarDTO carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", items, "dnguyen@miu.edu", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

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
                .header("Authorization", "Bearer " + jwtUserToken)
                .when().get("orders/manage?orderStatus=SHIPPED")
                .then()
                .statusCode(403)
                .and()
                .body("status", equalTo(403))
                .body("error", equalTo("Forbidden"));
    }

    @Test
    public void DeliveredOrder_Success() {
        String jwtToken = JwtUtils.generateEmployeeToken();
        String jwtUserToken = JwtUtils.generateUserToken();
        CardDTO cardDTO = new CardDTO("VISA", "0123456789", "08/29", "345");
        AddressDTO addressDTO = new AddressDTO("Dinh Thang Nguyen", "dnguyen@miu.edu", "641233112", "103 Main St", "FairField", 52556);

        Response response = given().contentType("application/json")
                .when().get("cars/b8f29c31-6f79-4cc4-b939-f5048d4a4f3c");
        CarDTO carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", items, "dnguyen@miu.edu", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

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

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .when().get("orders/manage?orderStatus=DELIVERED")
                .then()
                .statusCode(200)
                .and()
                .body("orderId", hasItem(orderId));
    }

    @Test
    public void DeliveredOrder_Fail_NonePermissionAccount() {
        String jwtToken = JwtUtils.generateEmployeeToken();
        String jwtUserToken = JwtUtils.generateUserToken();
        CardDTO cardDTO = new CardDTO("VISA", "0123456789", "08/29", "345");
        AddressDTO addressDTO = new AddressDTO("Dinh Thang Nguyen", "dnguyen@miu.edu", "641233112", "103 Main St", "FairField", 52556);

        Response response = given().contentType("application/json")
                .when().get("cars/b8f29c31-6f79-4cc4-b939-f5048d4a4f3c");
        CarDTO carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", items, "dnguyen@miu.edu", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

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
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(orderId)
                .when().post("orders/manage/deliver")
                .then()
                .statusCode(403)
                .and()
                .body("status", equalTo(403))
                .body("error", equalTo("Forbidden"));;
    }

    @Test
    public void DeliveredOrder_Fail_PlacedOrder() {
        String jwtToken = JwtUtils.generateEmployeeToken();
        String jwtUserToken = JwtUtils.generateUserToken();
        CardDTO cardDTO = new CardDTO("VISA", "0123456789", "08/29", "345");
        AddressDTO addressDTO = new AddressDTO("Dinh Thang Nguyen", "dnguyen@miu.edu", "641233112", "103 Main St", "FairField", 52556);

        Response response = given().contentType("application/json")
                .when().get("cars/b8f29c31-6f79-4cc4-b939-f5048d4a4f3c");
        CarDTO carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", items, "dnguyen@miu.edu", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

        response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtUserToken)
                .body(orderDTO)
                .when().post("orders");

        String orderId = response.jsonPath().getString("orderId");

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderId)
                .when().post("orders/manage/deliver")
                .then()
                .statusCode(500)
                .body("errorMessage", equalTo("Order " + orderId + " is not ready for deliver"));

    }
}