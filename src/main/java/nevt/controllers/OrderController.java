package nevt.controllers;

import nevt.common.constants.OrderStatus;
import nevt.dto.order.OrderDTO;
import nevt.models.order.Order;
import nevt.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.add(orderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @GetMapping("manage")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ResponseEntity<?> getOrderForEmployees(@RequestParam(required = false) String orderStatus) {

        OrderStatus status = null;
        if(!orderStatus.isBlank())
            status = OrderStatus.valueOf(orderStatus);

        List<OrderDTO> orders = orderService.findByOrderStatusOrderByDateDesc(status);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOrders(@RequestParam String email) {
        if (email == null) {
            Map<String,Object> map = new HashMap<>();
            map.put("isSuccess",false);
            map.put("fieldError", "please provide email");
            map.put("status", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        List<OrderDTO> orders = orderService.findByEmail(email);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @PostMapping("manage/ship")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ResponseEntity<?> shipOrder(@RequestBody String orderId) {
        OrderDTO orderDTO = orderService.shipOrder(orderId);
        return new ResponseEntity<>(orderDTO,HttpStatus.OK);
    }

    @PostMapping("manage/deliver")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public ResponseEntity<?> deliverOrder(@RequestBody String orderId) {
        OrderDTO orderDTO = orderService.deliverOrder(orderId);
        return new ResponseEntity<>(orderDTO,HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String,Object> fieldErrorMap = new HashMap<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            fieldErrorMap.put(e.getField(),e.getDefaultMessage());
        });
        Map<String,Object> map = new HashMap<>();
        map.put("isSuccess",false);
        map.put("fieldError", fieldErrorMap);
        map.put("status", HttpStatus.BAD_REQUEST);
        return  new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

}
