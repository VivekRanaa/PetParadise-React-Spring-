package com.petparadise.repository;

import com.petparadise.entity.Cart;
import com.petparadise.entity.CartItem;
import com.petparadise.entity.Product;
import com.petparadise.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Find cart items by cart
    List<CartItem> findByCart(Cart cart);

    // Find cart item by cart and product
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    // Find cart item by cart and service
    Optional<CartItem> findByCartAndService(Cart cart, Service service);

    // Delete cart items by cart
    void deleteByCart(Cart cart);

    // Check if cart item exists for cart and product
    boolean existsByCartAndProduct(Cart cart, Product product);

    // Check if cart item exists for cart and service
    boolean existsByCartAndService(Cart cart, Service service);
}
