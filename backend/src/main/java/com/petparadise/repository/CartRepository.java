package com.petparadise.repository;

import com.petparadise.entity.Cart;
import com.petparadise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Find cart by user
    Optional<Cart> findByUser(User user);

    // Find cart by user ID
    Optional<Cart> findByUserId(Long userId);

    // Delete cart by user
    void deleteByUser(User user);

    // Check if cart exists for user
    boolean existsByUser(User user);

    // Get cart items count for user
    @Query("SELECT COUNT(ci) FROM Cart c JOIN c.cartItems ci WHERE c.user = :user")
    Integer getCartItemsCountByUser(@Param("user") User user);
}
