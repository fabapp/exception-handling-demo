package de.fabiankrueger.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final WarehouseClient warehouseClient;

    public Order placeOrder(Order order) {
        log.info("placing order: "+ order);
        return warehouseClient.ship(order);
    }
}
