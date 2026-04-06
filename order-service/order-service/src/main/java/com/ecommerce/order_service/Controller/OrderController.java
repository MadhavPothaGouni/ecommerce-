package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.client.InventoryClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private InventoryClient inventoryClient;

    @PostMapping("/{productId}")
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackOrder")
    @Retry(name = "inventoryService", fallbackMethod = "fallbackOrder")
    public String placeOrder(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String token
    ) {

        // Call Inventory Service using Feign
        Boolean inStock = inventoryClient.isInStock(productId);

        if (inStock != null && inStock) {
            return "Order placed successfully";
        } else {
            return "Product out of stock";
        }
    }

    // Fallback Method (VERY IMPORTANT)
    public String fallbackOrder(Long productId, String token, Exception ex) {
        return "Inventory service is down. Please try again later.";
    }
}