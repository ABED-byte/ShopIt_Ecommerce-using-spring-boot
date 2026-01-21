package com.AbedProjects.ShopIt.Order;

import com.AbedProjects.ShopIt.Dtos.OrderResponseDto;
import com.AbedProjects.ShopIt.Dtos.PlaceOrderRequestDto;
import com.AbedProjects.ShopIt.Dtos.OrderItemRequestDto;
import com.AbedProjects.ShopIt.Dtos.UpdateOrderStatusRequest;
import com.AbedProjects.ShopIt.OrderItem.OrderItemEntity;
import com.AbedProjects.ShopIt.Product.ProductEntity;
import com.AbedProjects.ShopIt.Product.ProductRepo;
import com.AbedProjects.ShopIt.User.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public OrderResponseDto placeOrder(PlaceOrderRequestDto dto) {
        UserEntity user = currentUser();

        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order must contain items");
        }

        OrderEntity order = OrderEntity.builder()
                .userId(user.getId())
                .orderStatus(OrderStatus.PENDING)
                .build();

        BigDecimal total = BigDecimal.ZERO;
        for (OrderItemRequestDto itemDto : dto.getItems()) {
            ProductEntity product = productRepo.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + itemDto.getProductId()));

            if (product.getIsActive() != null && !product.getIsActive()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product is not active: " + itemDto.getProductId());
            }

            if (product.getProductPrice() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product has no price: " + itemDto.getProductId());
            }

            BigDecimal priceAtPurchase = product.getProductPrice();
            BigDecimal lineTotal = priceAtPurchase.multiply(BigDecimal.valueOf(itemDto.getQuantity()));
            total = total.add(lineTotal);

            OrderItemEntity item = OrderItemEntity.builder()
                    .order(order)
                    .productId(itemDto.getProductId())
                    .quantity(itemDto.getQuantity())
                    .priceAtPurchase(priceAtPurchase)
                    .build();
            order.getItems().add(item);
        }

        order.setTotalAmount(total);

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

    public void updateOrderStatus(Long id, UpdateOrderStatusRequest status) {
        OrderEntity order = orderRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        order.setOrderStatus(status.getOrderStatus());
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

