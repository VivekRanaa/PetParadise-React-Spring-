package com.petparadise.config;

import com.petparadise.entity.Product;
import com.petparadise.entity.Service;
import com.petparadise.repository.ProductRepository;
import com.petparadise.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeProducts();
        initializeServices();
    }

    private void initializeProducts() {
        // Only initialize if no products exist
        if (productRepository.count() == 0) {
            List<Product> products = Arrays.asList(
                // Dog Food
                Product.builder()
                    .name("Premium Dog Food - Adult")
                    .description("High-quality nutrition for adult dogs with real chicken and vegetables")
                    .price(new BigDecimal("45.99"))
                    .originalPrice(new BigDecimal("55.99"))
                    .category("Food")
                    .brand("PetParadise Premium")
                    .stockQuantity(150)
                    .imageUrl("/Images/dog-food-adult.jpg")
                    .isActive(true)
                    .rating(4.5)
                    .reviewCount(120)
                    .build(),
                
                Product.builder()
                    .name("Puppy Starter Kit Food")
                    .description("Specially formulated nutrition for growing puppies")
                    .price(new BigDecimal("38.99"))
                    .originalPrice(new BigDecimal("42.99"))
                    .category("Food")
                    .brand("PetParadise Premium")
                    .stockQuantity(80)
                    .imageUrl("/Images/puppy-food.jpg")
                    .isActive(true)
                    .rating(4.7)
                    .reviewCount(95)
                    .build(),

                // Cat Food
                Product.builder()
                    .name("Premium Cat Food - Indoor")
                    .description("Complete nutrition for indoor cats with hairball control")
                    .price(new BigDecimal("35.99"))
                    .originalPrice(new BigDecimal("40.99"))
                    .category("Food")
                    .brand("PetParadise Premium")
                    .stockQuantity(120)
                    .imageUrl("/Images/cat-food-indoor.jpg")
                    .isActive(true)
                    .rating(4.3)
                    .reviewCount(78)
                    .build(),

                // Toys
                Product.builder()
                    .name("Interactive Puzzle Toy")
                    .description("Mental stimulation toy for dogs of all sizes")
                    .price(new BigDecimal("24.99"))
                    .originalPrice(new BigDecimal("29.99"))
                    .category("Toys")
                    .brand("PetPlay")
                    .stockQuantity(200)
                    .imageUrl("/Images/puzzle-toy.jpg")
                    .isActive(true)
                    .rating(4.6)
                    .reviewCount(150)
                    .build(),

                Product.builder()
                    .name("Catnip Mouse Set")
                    .description("Set of 5 catnip-filled mice toys for cats")
                    .price(new BigDecimal("12.99"))
                    .originalPrice(new BigDecimal("15.99"))
                    .category("Toys")
                    .brand("FelinePlay")
                    .stockQuantity(300)
                    .imageUrl("/Images/catnip-mice.jpg")
                    .isActive(true)
                    .rating(4.4)
                    .reviewCount(210)
                    .build(),

                Product.builder()
                    .name("Rope Tug Toy")
                    .description("Durable rope toy perfect for interactive play")
                    .price(new BigDecimal("8.99"))
                    .originalPrice(new BigDecimal("11.99"))
                    .category("Toys")
                    .brand("PetPlay")
                    .stockQuantity(250)
                    .imageUrl("/Images/rope-toy.jpg")
                    .isActive(true)
                    .rating(4.2)
                    .reviewCount(85)
                    .build(),

                // Accessories
                Product.builder()
                    .name("Adjustable Dog Collar")
                    .description("Comfortable and durable collar with quick-release buckle")
                    .price(new BigDecimal("18.99"))
                    .originalPrice(new BigDecimal("22.99"))
                    .category("Accessories")
                    .brand("PetGear")
                    .stockQuantity(180)
                    .imageUrl("/Images/dog-collar.jpg")
                    .isActive(true)
                    .rating(4.5)
                    .reviewCount(130)
                    .build(),

                Product.builder()
                    .name("Retractable Dog Leash")
                    .description("16ft retractable leash with comfortable grip handle")
                    .price(new BigDecimal("25.99"))
                    .originalPrice(new BigDecimal("32.99"))
                    .category("Accessories")
                    .brand("PetGear")
                    .stockQuantity(100)
                    .imageUrl("/Images/retractable-leash.jpg")
                    .isActive(true)
                    .rating(4.3)
                    .reviewCount(67)
                    .build(),

                Product.builder()
                    .name("Cat Scratching Post")
                    .description("Multi-level scratching post with sisal rope and toys")
                    .price(new BigDecimal("69.99"))
                    .originalPrice(new BigDecimal("89.99"))
                    .category("Accessories")
                    .brand("FelineHome")
                    .stockQuantity(45)
                    .imageUrl("/Images/scratching-post.jpg")
                    .isActive(true)
                    .rating(4.7)
                    .reviewCount(92)
                    .build(),

                // Health & Care
                Product.builder()
                    .name("Pet Shampoo - Sensitive Skin")
                    .description("Gentle, hypoallergenic shampoo for sensitive pets")
                    .price(new BigDecimal("16.99"))
                    .originalPrice(new BigDecimal("19.99"))
                    .category("Health & Care")
                    .brand("PetParadise Care")
                    .stockQuantity(90)
                    .imageUrl("/Images/pet-shampoo.jpg")
                    .isActive(true)
                    .rating(4.4)
                    .reviewCount(115)
                    .build(),

                Product.builder()
                    .name("Dental Chews for Dogs")
                    .description("Promotes healthy teeth and fresh breath")
                    .price(new BigDecimal("14.99"))
                    .originalPrice(new BigDecimal("17.99"))
                    .category("Health & Care")
                    .brand("DentalPet")
                    .stockQuantity(160)
                    .imageUrl("/Images/dental-chews.jpg")
                    .isActive(true)
                    .rating(4.2)
                    .reviewCount(88)
                    .build(),

                Product.builder()
                    .name("Pet Nail Clippers")
                    .description("Professional-grade nail clippers with safety guard")
                    .price(new BigDecimal("12.99"))
                    .originalPrice(new BigDecimal("16.99"))
                    .category("Health & Care")
                    .brand("PetGroomer Pro")
                    .stockQuantity(75)
                    .imageUrl("/Images/nail-clippers.jpg")
                    .isActive(true)
                    .rating(4.6)
                    .reviewCount(156)
                    .build()
            );

            productRepository.saveAll(products);
            System.out.println("Initialized " + products.size() + " products successfully!");
        }
    }

    private void initializeServices() {
        // Only initialize if no services exist
        if (serviceRepository.count() == 0) {
            List<Service> services = Arrays.asList(
                // Grooming Services
                Service.builder()
                    .name("Full Service Grooming")
                    .serviceType("Grooming")
                    .description("Complete grooming package including bath, nail trim, ear cleaning, and haircut")
                    .priceRange("$50 - $120")
                    .duration("2-3 hours")
                    .imageUrl("/Images/groom.jpg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Basic Bath & Brush")
                    .serviceType("Grooming")
                    .description("Refreshing bath with premium shampoo and thorough brushing")
                    .priceRange("$25 - $45")
                    .duration("1-1.5 hours")
                    .imageUrl("/Images/bath.jpg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Nail Trimming")
                    .serviceType("Grooming")
                    .description("Professional nail trimming and filing for your pet's comfort")
                    .priceRange("$15 - $25")
                    .duration("15-30 minutes")
                    .imageUrl("/Images/nail-trim.jpg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Dental Cleaning")
                    .serviceType("Grooming")
                    .description("Professional dental cleaning and oral health check")
                    .priceRange("$40 - $80")
                    .duration("45-60 minutes")
                    .imageUrl("/Images/dental-clean.jpg")
                    .isActive(true)
                    .build(),

                // Boarding Services
                Service.builder()
                    .name("Overnight Boarding")
                    .serviceType("Boarding")
                    .description("Safe and comfortable overnight stay with 24/7 supervision")
                    .priceRange("$45 - $85 per night")
                    .duration("24 hours")
                    .imageUrl("/Images/b1.jpeg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Extended Stay Boarding")
                    .serviceType("Boarding")
                    .description("Long-term boarding for vacations and extended trips")
                    .priceRange("$40 - $75 per night")
                    .duration("Multiple days")
                    .imageUrl("/Images/b2.jpeg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Luxury Suite Boarding")
                    .serviceType("Boarding")
                    .description("Premium boarding experience with private suites and extra amenities")
                    .priceRange("$80 - $150 per night")
                    .duration("24 hours")
                    .imageUrl("/Images/luxury-suite.jpg")
                    .isActive(true)
                    .build(),

                // Daycare & Play Services
                Service.builder()
                    .name("Doggy Daycare")
                    .serviceType("Daycare")
                    .description("Full day of supervised play and socialization")
                    .priceRange("$35 - $55 per day")
                    .duration("8-10 hours")
                    .imageUrl("/Images/day1.jpeg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Half-Day Play Session")
                    .serviceType("Daycare")
                    .description("4-hour supervised play session with other pets")
                    .priceRange("$20 - $35")
                    .duration("4 hours")
                    .imageUrl("/Images/play1.jpeg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Private Play Time")
                    .serviceType("Daycare")
                    .description("One-on-one play session for shy or special needs pets")
                    .priceRange("$25 - $45")
                    .duration("1-2 hours")
                    .imageUrl("/Images/play2.jpeg")
                    .isActive(true)
                    .build(),

                // Pool & Spa Services
                Service.builder()
                    .name("Swimming Session")
                    .serviceType("Pool")
                    .description("Supervised swimming session in our heated pool")
                    .priceRange("$30 - $50")
                    .duration("30-45 minutes")
                    .imageUrl("/Images/pool.jpg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Hydrotherapy Treatment")
                    .serviceType("Pool")
                    .description("Therapeutic water exercise for rehabilitation and fitness")
                    .priceRange("$60 - $100")
                    .duration("45-60 minutes")
                    .imageUrl("/Images/hydrotherapy.jpg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Pool Party Package")
                    .serviceType("Pool")
                    .description("Group swimming session for multiple pets")
                    .priceRange("$80 - $150")
                    .duration("2-3 hours")
                    .imageUrl("/Images/poolparty.jpg")
                    .isActive(true)
                    .build(),

                // Training Services
                Service.builder()
                    .name("Basic Obedience Training")
                    .serviceType("Training")
                    .description("Essential commands and behavioral training for puppies and adult dogs")
                    .priceRange("$60 - $100 per session")
                    .duration("1 hour")
                    .imageUrl("/Images/basic-training.jpg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Advanced Training")
                    .serviceType("Training")
                    .description("Advanced commands and specialized behavioral modification")
                    .priceRange("$80 - $120 per session")
                    .duration("1-1.5 hours")
                    .imageUrl("/Images/advanced-training.jpg")
                    .isActive(true)
                    .build(),

                Service.builder()
                    .name("Puppy Socialization Class")
                    .serviceType("Training")
                    .description("Group socialization class for young puppies")
                    .priceRange("$40 - $60 per class")
                    .duration("1 hour")
                    .imageUrl("/Images/puppy-class.jpg")
                    .isActive(true)
                    .build()
            );

            serviceRepository.saveAll(services);
            System.out.println("Initialized " + services.size() + " services successfully!");
        }
    }
}
