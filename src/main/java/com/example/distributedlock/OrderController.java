package com.example.distributedlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.ResourceBundle;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepo orderRepo;

    @PutMapping("{order_id}")
    ResponseEntity<Object> update(@PathVariable("order_id") Long orderId,
                                       @RequestBody Order body) throws InterruptedException {
        return ResponseEntity.ok(orderService.updateOrder(orderId, body));
    }

    @PostMapping()
    ResponseEntity<Object> create(@RequestBody Order body) {
        Order order = new Order();
        order.setName(body.getName());
        orderRepo.save(order);

        return ResponseEntity.ok(order);
    }

    @GetMapping()
    ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(orderRepo.findAll());
    }

    @GetMapping("/test/{message_id}")
    ResponseEntity<Object> test(@PathVariable("message_id") String messageId) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", Locale.forLanguageTag("vi"));
        String message = resourceBundle.getString(messageId);
        return ResponseEntity.ok(message);
    }
}
