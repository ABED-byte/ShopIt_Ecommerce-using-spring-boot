package com.AbedProjects.ShopIt.Order;

import com.AbedProjects.ShopIt.Dtos.OrderResponseDto;
import com.AbedProjects.ShopIt.Dtos.PlaceOrderRequestDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // CUSTOMER (authenticated)
    @PostMapping
    ResponseEntity<OrderResponseDto> placeOrder(@Valid @RequestBody PlaceOrderRequestDto dto) {
        return ResponseEntity.ok(orderService.placeOrder(dto));
    }

    // CUSTOMER (authenticated) - only their orders
    @GetMapping("/me")
    ResponseEntity<List<OrderResponseDto>> getMyOrders() {
        return ResponseEntity.ok(orderService.getMyOrders());
    }

    // CUSTOMER (authenticated) - only their order by id
    @GetMapping("/me/{id}")
    ResponseEntity<OrderResponseDto> getMyOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // ADMIN only - view any order
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/{id}")
    ResponseEntity<OrderResponseDto> getAnyOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getAnyOrderById(id));
    }

    // ADMIN only - update status
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{id}/status")
    ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}

