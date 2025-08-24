import React, { useState } from "react";
import "./PetShop.css";
import { FiSearch, FiShoppingCart, FiHeart, FiStar } from "react-icons/fi";
import { FaPaw, FaUtensils, FaTshirt, FaGamepad } from "react-icons/fa";
function PetShop() {
    const [searchTerm, setSearchTerm] = useState("");
    const [selectedCategory, setSelectedCategory] = useState("All");
    const [cart, setCart] = useState([]);
    const [wishlist, setWishlist] = useState([]);

    const categories = ["All", "Food", "Toys", "Accessories", "Health"];

    const products = [
        {
            id: 1,
            name: "Premium Dog Food",
            category: "Food",
            price: 1200,
            originalPrice: 1500,
            image: "/Images/p1.jpg",
            description: "High-quality nutrition for your furry friend",
            rating: 4.5,
            reviews: 128,
            badge: "Best Seller"
        },
        {
            id: 2,
            name: "Interactive Dog Toy",
            category: "Toys",
            price: 450,
            originalPrice: 600,
            image: "/Images/p2.jpg",
            description: "Keep your pet entertained for hours",
            rating: 4.2,
            reviews: 89,
            badge: "New"
        },
        {
            id: 3,
            name: "Comfortable Pet Bed",
            category: "Accessories",
            price: 2500,
            originalPrice: 3000,
            image: "/Images/bath.jpg",
            description: "Ultra-soft and cozy sleeping space",
            rating: 4.8,
            reviews: 203,
            badge: "Popular"
        },
        {
            id: 4,
            name: "Pet Health Supplements",
            category: "Health",
            price: 800,
            originalPrice: 1000,
            image: "/Images/groom.jpg",
            description: "Essential vitamins for pet wellness",
            rating: 4.6,
            reviews: 156,
            badge: "Recommended"
        },
        {
            id: 5,
            name: "Cat Food Premium",
            category: "Food",
            price: 950,
            originalPrice: 1200,
            image: "/Images/pool.jpg",
            description: "Balanced nutrition for cats of all ages",
            rating: 4.3,
            reviews: 94,
            badge: ""
        },
        {
            id: 6,
            name: "Pet Grooming Kit",
            category: "Accessories",
            price: 1800,
            originalPrice: 2200,
            image: "/Images/poolparty.jpg",
            description: "Complete grooming solution at home",
            rating: 4.7,
            reviews: 167,
            badge: "Pro Choice"
        }
    ];

    const filteredProducts = products.filter(product => {
        const matchesCategory = selectedCategory === "All" || product.category === selectedCategory;
        const matchesSearch = product.name.toLowerCase().includes(searchTerm.toLowerCase());
        return matchesCategory && matchesSearch;
    });

    const addToCart = (product) => {
        setCart(prev => [...prev, product]);
    };

    const toggleWishlist = (productId) => {
        setWishlist(prev => 
            prev.includes(productId) 
                ? prev.filter(id => id !== productId)
                : [...prev, productId]
        );
    };

    const cartTotal = cart.reduce((total, item) => total + item.price, 0);

    const renderStars = (rating) => {
        return Array.from({ length: 5 }, (_, index) => (
            <FiStar 
                key={index} 
                className="star" 
                style={{ 
                    color: index < Math.floor(rating) ? '#fbbf24' : '#d1d5db',
                    fill: index < Math.floor(rating) ? '#fbbf24' : 'none'
                }} 
            />
        ));
    };

    return (
        <div className="petshop-page page-wrapper">
            <div className="petshop-container">
                <div className="petshop-header">
                    <h2>Pet Paradise Store</h2>
                    <p>
                        Everything your pet needs, from premium food to fun toys and essential accessories. 
                        Shop with confidence for quality products that keep your furry friends happy and healthy.
                    </p>
                </div>

                <div className="search-section">
                    <div className="search-container">
                        <div className="search-input-wrapper">
                            <FiSearch className="search-icon" />
                            <input
                                type="text"
                                className="search-input"
                                placeholder="Search for products..."
                                value={searchTerm}
                                onChange={(e) => setSearchTerm(e.target.value)}
                            />
                        </div>
                        
                        <div className="category-filters">
                            {categories.map(category => (
                                <button
                                    key={category}
                                    className={`category-filter ${selectedCategory === category ? 'active' : ''}`}
                                    onClick={() => setSelectedCategory(category)}
                                >
                                    {category}
                                </button>
                            ))}
                        </div>
                    </div>
                </div>

                <div className="products-section">
                    <div className="products-grid">
                        {filteredProducts.map(product => (
                            <div key={product.id} className="product-card">
                                <div className="product-image-wrapper">
                                    <img src={product.image} alt={product.name} className="product-image" />
                                    {product.badge && <div className="product-badge">{product.badge}</div>}
                                </div>
                                
                                <div className="product-info">
                                    <div className="product-category">{product.category}</div>
                                    <h3 className="product-title">{product.name}</h3>
                                    <p className="product-description">{product.description}</p>
                                    
                                    <div className="rating">
                                        <div className="stars">{renderStars(product.rating)}</div>
                                        <span className="rating-text">({product.reviews} reviews)</span>
                                    </div>
                                    
                                    <div className="product-price">
                                        <span className="price-current">₹{product.price}</span>
                                        {product.originalPrice && (
                                            <span className="price-original">₹{product.originalPrice}</span>
                                        )}
                                    </div>
                                    
                                    <div className="product-actions">
                                        <button 
                                            className="add-to-cart-btn"
                                            onClick={() => addToCart(product)}
                                        >
                                            <FiShoppingCart />
                                            Add to Cart
                                        </button>
                                        <button 
                                            className="wishlist-btn"
                                            onClick={() => toggleWishlist(product.id)}
                                        >
                                            <FiHeart 
                                                style={{ 
                                                    color: wishlist.includes(product.id) ? '#ef4444' : '#6b7280',
                                                    fill: wishlist.includes(product.id) ? '#ef4444' : 'none'
                                                }} 
                                            />
                                        </button>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>

                {cart.length > 0 && (
                    <div className="cart-summary">
                        <h4>Cart Summary</h4>
                        <p>Items: {cart.length}</p>
                        <div className="cart-total">₹{cartTotal}</div>
                        <button className="checkout-btn">
                            <FiShoppingCart />
                            Checkout
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
}

export default PetShop