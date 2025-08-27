package com.petparadise.repository;

import com.petparadise.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find products by category
    List<Product> findByCategory(String category);

    // Find products by category with pagination
    Page<Product> findByCategory(String category, Pageable pageable);

    // Find products in stock
    List<Product> findByInStockTrue();

    // Find products in stock with pagination
    Page<Product> findByInStockTrue(Pageable pageable);

    // Find products by name containing (case-insensitive)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByNameContaining(@Param("name") String name);

    // Find products by name containing with pagination
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

    // Find products by price range
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Find products by price range with pagination
    Page<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    // Find products with rating above certain value
    @Query("SELECT p FROM Product p WHERE p.rating >= :minRating")
    List<Product> findByRatingGreaterThanEqual(@Param("minRating") BigDecimal minRating);

    // Find products with discounts
    @Query("SELECT p FROM Product p WHERE p.originalPrice IS NOT NULL AND p.originalPrice > p.price")
    List<Product> findProductsWithDiscount();

    // Find products with discounts with pagination
    @Query("SELECT p FROM Product p WHERE p.originalPrice IS NOT NULL AND p.originalPrice > p.price")
    Page<Product> findProductsWithDiscount(Pageable pageable);

    // Find products by badge
    List<Product> findByBadge(String badge);

    // Find products with low stock
    @Query("SELECT p FROM Product p WHERE p.stockQuantity <= :threshold AND p.inStock = true")
    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);

    // Search products by multiple criteria
    @Query("SELECT p FROM Product p WHERE " +
           "(:category IS NULL OR p.category = :category) AND " +
           "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
           "(:inStock IS NULL OR p.inStock = :inStock)")
    Page<Product> findProductsByCriteria(
            @Param("category") String category,
            @Param("name") String name,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("inStock") Boolean inStock,
            Pageable pageable);

    // Find top-rated products
    @Query("SELECT p FROM Product p WHERE p.rating IS NOT NULL ORDER BY p.rating DESC")
    List<Product> findTopRatedProducts(Pageable pageable);

    // Find best sellers (products with most reviews)
    @Query("SELECT p FROM Product p WHERE p.reviews IS NOT NULL ORDER BY p.reviews DESC")
    List<Product> findBestSellers(Pageable pageable);

    // Count products by category
    @Query("SELECT COUNT(p) FROM Product p WHERE p.category = :category")
    Long countByCategory(@Param("category") String category);

    // Get all unique categories
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.category IS NOT NULL ORDER BY p.category")
    List<String> findAllCategories();

    // Get all unique badges
    @Query("SELECT DISTINCT p.badge FROM Product p WHERE p.badge IS NOT NULL ORDER BY p.badge")
    List<String> findAllBadges();
}
