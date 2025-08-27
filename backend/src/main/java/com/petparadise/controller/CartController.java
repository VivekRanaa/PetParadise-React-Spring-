package com.petparadise.controller;

import com.petparadise.dto.AddToCartRequest;
import com.petparadise.dto.UpdateCartItemRequest;
import com.petparadise.entity.Cart;
import com.petparadise.entity.CartItem;
import com.petparadise.entity.User;
import com.petparadise.service.CartService;
import com.petparadise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174"})
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Cart> getUserCart(Authentication authentication) {
        User user = getCurrentUser(authentication);
        Cart cart = cartService.getUserCart(user);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItem>> getCartItems(Authentication authentication) {
        User user = getCurrentUser(authentication);
        List<CartItem> cartItems = cartService.getCartItems(user);
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCartItemsCount(Authentication authentication) {
        User user = getCurrentUser(authentication);
        Integer count = cartService.getCartItemsCount(user);
        return ResponseEntity.ok(count != null ? count : 0);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@Valid @RequestBody AddToCartRequest request, 
                                       Authentication authentication) {
        try {
            User user = getCurrentUser(authentication);
            Cart cart;

            if ("product".equalsIgnoreCase(request.getType())) {
                cart = cartService.addProductToCart(user, request.getItemId(), request.getQuantity());
            } else if ("service".equalsIgnoreCase(request.getType())) {
                cart = cartService.addServiceToCart(user, request.getItemId(), request.getQuantity());
            } else {
                return ResponseEntity.badRequest().body("Invalid type. Must be 'product' or 'service'");
            }

            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding to cart: " + e.getMessage());
        }
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<?> updateCartItem(@PathVariable Long cartItemId,
                                            @Valid @RequestBody UpdateCartItemRequest request,
                                            Authentication authentication) {
        try {
            User user = getCurrentUser(authentication);
            Cart cart = cartService.updateCartItemQuantity(user, cartItemId, request.getQuantity());
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating cart item: " + e.getMessage());
        }
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<?> removeCartItem(@PathVariable Long cartItemId,
                                            Authentication authentication) {
        try {
            User user = getCurrentUser(authentication);
            Cart cart = cartService.removeCartItem(user, cartItemId);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error removing cart item: " + e.getMessage());
        }
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(Authentication authentication) {
        try {
            User user = getCurrentUser(authentication);
            cartService.clearCart(user);
            return ResponseEntity.ok("Cart cleared successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error clearing cart: " + e.getMessage());
        }
    }

    private User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
