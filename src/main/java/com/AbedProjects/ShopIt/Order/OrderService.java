package com.AbedProjects.ShopIt.Order;

import com.AbedProjects.ShopIt.Dtos.OrderResponseDto;
import com.AbedProjects.ShopIt.Dtos.PlaceOrderRequestDto;
import com.AbedProjects.ShopIt.User.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public OrderResponseDto placeOrder(PlaceOrderRequestDto dto) {
        UserEntity user = currentUser();

        OrderEntity order = OrderEntity.builder()
                .userId(user.getId())
                .totalAmount(dto.getTotalAmount())
                .orderStatus(OrderStatus.PENDING)
                .build();

        return new OrderResponseDto(orderRepo.save(order));
    }

    public List<OrderResponseDto> getMyOrders() {
        UserEntity user = currentUser();
        return orderRepo.findByUserIdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(OrderResponseDto::new)
                .toList();
    }

    public OrderResponseDto getOrderById(Long id) {
        UserEntity user = currentUser();
        OrderEntity order = orderRepo.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        return new OrderResponseDto(order);
    }

    public OrderResponseDto getAnyOrderById(Long id) {
        OrderEntity order = orderRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        return new OrderResponseDto(order);
    }

    public void updateOrderStatus(Long id, OrderStatus status) {
        OrderEntity order = orderRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        order.setOrderStatus(status);
        orderRepo.save(order);
    }

    private UserEntity currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UserEntity user)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return user;
    }
}

