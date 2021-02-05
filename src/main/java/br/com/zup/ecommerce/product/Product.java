package br.com.zup.ecommerce.product;

import br.com.zup.ecommerce.category.Category;
import br.com.zup.ecommerce.user.User;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Positive
    @Column(nullable = false)
    private BigDecimal price;

    @Min(0)
    @Column(nullable = false)
    private Long availableQuantity;

    @Size(max = 1000)
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<ProductImage> images = new HashSet<>();

    @Deprecated
    public Product() {

    }

    public Product(String name, BigDecimal price, Long availableQuantity, String description,Category category, User user) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.category = category;
        this.user = user;
    }

    public void attachImages(Set<String> imageLinks) {
        Set<ProductImage> productImages = imageLinks
                                                .stream()
                                                .map(imageLink -> new ProductImage(imageLink, this))
                                                .collect(Collectors.toSet());

        images.addAll(productImages);
    }

    public boolean belongsToUser(User user) {
        return this.user.equals(user);
    }

    public User getOwner() {
        return user;
    }
}
