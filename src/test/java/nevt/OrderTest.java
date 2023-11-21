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
    public void testCreateCarSuccess() {
        String jwtToken = JwtUtils.generateUserToken();

        CardDTO cardDTO = new CardDTO("VISA","0123456789", "08/29","345");
        AddressDTO addressDTO = new AddressDTO("John Smith","johnsmith@gmail.com","641233112", "103 Main St", "FairField", 52556);

        OrderDTO orderDTO = new OrderDTO("", null, "johnsmith@gmail.com", OrderStatus.PLACED, LocalDate.now(), 99990, cardDTO, addressDTO);

        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderDTO)
                .when().post("orders");

        Object abc = response.jsonPath();

        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderDTO)
                .when().post("orders")
                .then()
                .statusCode(200)
                .and()
                .body("email", equalTo("johnsmith@gmail.com"))
                .body("orderStatus", equalTo("PLACED"))
                .body("total", equalTo(99990F))
                .body("card.type", equalTo("VISA"))
                .body("card.number", equalTo("0123456789"))
                .body("address.name", equalTo("John Smith"))
                .body("address.phone", equalTo("641233112"));
    }

    @Test
    public void testCreateCarFailNoCard() {
        String jwtToken = JwtUtils.generateUserToken();

        AddressDTO addressDTO = new AddressDTO("John Smith","johnsmith@gmail.com","641233112", "103 Main St", "FairField", 52556);

        Response response = given().contentType("application/json")
                .when().get("cars/b8f29c31-6f79-4cc4-b939-f5048d4a4f3c");
        CarDTO carDTO = response.as(CarDTO.class);

        Collection<OrderItemDTO> items = new ArrayList<>();
        items.add(new OrderItemDTO(carDTO, 1, 99990));

        OrderDTO orderDTO = new OrderDTO("", items, "johnsmith@gmail.com", OrderStatus.PLACED, LocalDate.now(), 99990, null, addressDTO);
        response = given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderDTO)
                .when().post("orders");

        Object abc = response.jsonPath();
        given().contentType("application/json")
                .header("Authorization", "Bearer " + jwtToken)
                .body(orderDTO)
                .when().post("orders")
                .then()
                .statusCode(500)
                .and()
                .body("data", is(nullValue()))
                .body("fieldError.card", equalTo("must not be empty"))
                .body("status", equalTo("BAD_REQUEST"));
    }
}