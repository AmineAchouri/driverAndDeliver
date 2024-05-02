package fr.carrefour.kata.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import fr.carrefour.kata.Order;
import fr.carrefour.kata.OrderBinder;
import fr.carrefour.kata.OrderNotFoundException;
import fr.carrefour.kata.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final OrderBinder orderBinder;

    Map<UUID, Order> orderDataBase = new HashMap<>();

    public OrderStatus statusCheck(UUID orderUuid) {
        return Optional.ofNullable(orderDataBase.get(orderUuid))
                .orElseThrow(() -> new OrderNotFoundException("Order not found")).getOrderStatus();
    }

    public Order placeOrder(Order orderIn) {
        log.debug("placeOrder orderIn: {}", orderIn);

        var order = Order.builder()//
                .itemName(orderIn.getItemName())//
                .orderUuid(UUID.randomUUID())//
                .orderStatus(OrderStatus.PENDING)//
                .build();

        orderDataBase.put(order.getOrderUuid(), order);

        shipIt(order);
        return order;
    }

    @StreamListener(OrderBinder.SHIPPING_IN)
    public void shipIt(@Payload Order orderIn) {
        log.debug("shipIt orderIn: {}", orderIn);
        orderIn.setOrderStatus(OrderStatus.SHIPPED);
        orderDataBase.put(orderIn.getOrderUuid(), orderIn);

        log.info("ItemID: {} has been Shipped", orderIn.getOrderUuid());
    }

}