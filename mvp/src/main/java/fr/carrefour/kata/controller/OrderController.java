package fr.carrefour.kata.controller;


import fr.carrefour.kata.Order;
import fr.carrefour.kata.OrderStatus;
import fr.carrefour.kata.service.DeliveryService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class OrderController {

    private final DeliveryService deliveryService;

    @PostMapping("order")
    public Order chooseDelivery(@RequestBody @NotNull(message = "Invalid Order") Order order) {
        return deliveryService.placeOrder(order);
    }

    @GetMapping("order/status/{orderUuid}")
    public OrderStatus statusCheck(@PathVariable("orderUuid") UUID orderUuid) {
        return deliveryService.statusCheck(orderUuid);
    }
}