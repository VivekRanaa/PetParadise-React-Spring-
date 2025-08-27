package com.petparadise.service;

import com.petparadise.dto.ProductDTO;
import com.petparadise.entity.Product;
import com.petparadise.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Create a new product
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    // Get all products with pagination
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductDTO> productDTOs = products.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(productDTOs, pageable, products.getTotalElements());
    }

    // Get product by ID
    public Optional<ProductDTO> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDTO);
    }

    // Update product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            updateProductFields(product, productDTO);
            Product updatedProduct = productRepository.save(product);
            return convertToDTO(updatedProduct);
        }
        throw new RuntimeException("Product not found with id: " + id);
    }

    // Delete product
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    // Get products by category
    public Page<ProductDTO> getProductsByCategory(String category, Pageable pageable) {
        Page<Product> products = productRepository.findByCategory(category, pageable);
        List<ProductDTO> productDTOs = products.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(productDTOs, pageable, products.getTotalElements());
    }

    // Get products in stock
    public Page<ProductDTO> getProductsInStock(Pageable pageable) {
        Page<Product> products = productRepository.findByIsActiveTrue(pageable);
        List<ProductDTO> productDTOs = products.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(productDTOs, pageable, products.getTotalElements());
    }

    // Search products by name
    public Page<ProductDTO> searchProductsByName(String name, Pageable pageable) {
        Page<Product> products = productRepository.findByNameContaining(name, pageable);
        List<ProductDTO> productDTOs = products.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(productDTOs, pageable, products.getTotalElements());
    }

    // Get products by price range
    public Page<ProductDTO> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        Page<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        List<ProductDTO> productDTOs = products.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(productDTOs, pageable, products.getTotalElements());
    }

    // Get products with discounts
    public Page<ProductDTO> getProductsWithDiscount(Pageable pageable) {
        Page<Product> products = productRepository.findProductsWithDiscount(pageable);
        List<ProductDTO> productDTOs = products.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(productDTOs, pageable, products.getTotalElements());
    }

    // Search products by multiple criteria
    public Page<ProductDTO> searchProducts(String category, String name, BigDecimal minPrice, 
                                         BigDecimal maxPrice, Boolean isActive, Pageable pageable) {
        Page<Product> products = productRepository.findProductsByCriteria(
                category, name, minPrice, maxPrice, isActive, pageable);
        List<ProductDTO> productDTOs = products.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(productDTOs, pageable, products.getTotalElements());
    }

    // Get top-rated products
    public List<ProductDTO> getTopRatedProducts(int limit) {
        List<Product> products = productRepository.findTopRatedProducts(
                org.springframework.data.domain.PageRequest.of(0, limit));
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get best sellers
    public List<ProductDTO> getBestSellers(int limit) {
        List<Product> products = productRepository.findBestSellers(
                org.springframework.data.domain.PageRequest.of(0, limit));
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get low stock products
    public List<ProductDTO> getLowStockProducts(int threshold) {
        List<Product> products = productRepository.findLowStockProducts(threshold);
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get all categories
    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    // Get product statistics
    public ProductStatistics getProductStatistics() {
        long totalProducts = productRepository.count();
        long inStockProducts = productRepository.findByIsActiveTrue().size();
        long outOfStockProducts = totalProducts - inStockProducts;
        List<String> categories = productRepository.findAllCategories();
        
        return new ProductStatistics(totalProducts, inStockProducts, outOfStockProducts, categories.size());
    }

    // Helper method to convert Entity to DTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        
        // Basic fields
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setOriginalPrice(product.getOriginalPrice());
        dto.setImageUrl(product.getImageUrl());
        dto.setDescription(product.getDescription());
        dto.setStockQuantity(product.getStockQuantity());
        
        // Field mappings with type conversions
        if (product.getRating() != null) {
            dto.setRating(BigDecimal.valueOf(product.getRating()));
        }
        dto.setReviews(product.getReviewCount());
        dto.setBadge(product.getBrand());
        dto.setInStock(product.getIsActive());
        
        // Date formatting
        if (product.getCreatedAt() != null) {
            dto.setCreatedAt(product.getCreatedAt().format(formatter));
        }
        if (product.getUpdatedAt() != null) {
            dto.setUpdatedAt(product.getUpdatedAt().format(formatter));
        }
        return dto;
    }

    // Helper method to update product fields
    private void updateProductFields(Product existingProduct, ProductDTO productDTO) {
        existingProduct.setName(productDTO.getName());
        existingProduct.setCategory(productDTO.getCategory());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setOriginalPrice(productDTO.getOriginalPrice());
        existingProduct.setImageUrl(productDTO.getImageUrl());
        existingProduct.setDescription(productDTO.getDescription());
        
        // Convert BigDecimal to Double for rating
        if (productDTO.getRating() != null) {
            existingProduct.setRating(productDTO.getRating().doubleValue());
        }
        
        // Map DTO fields to Entity fields
        existingProduct.setReviewCount(productDTO.getReviews());
        existingProduct.setBrand(productDTO.getBadge()); // Using brand field instead of badge
        existingProduct.setIsActive(productDTO.getInStock()); // Using isActive instead of inStock
        existingProduct.setStockQuantity(productDTO.getStockQuantity());
    }

    // Inner class for product statistics
    public static class ProductStatistics {
        private final long totalProducts;
        private final long inStockProducts;
        private final long outOfStockProducts;
        private final long totalCategories;

        public ProductStatistics(long totalProducts, long inStockProducts, 
                               long outOfStockProducts, long totalCategories) {
            this.totalProducts = totalProducts;
            this.inStockProducts = inStockProducts;
            this.outOfStockProducts = outOfStockProducts;
            this.totalCategories = totalCategories;
        }

        // Getters
        public long getTotalProducts() { return totalProducts; }
        public long getInStockProducts() { return inStockProducts; }
        public long getOutOfStockProducts() { return outOfStockProducts; }
        public long getTotalCategories() { return totalCategories; }
    }
}
