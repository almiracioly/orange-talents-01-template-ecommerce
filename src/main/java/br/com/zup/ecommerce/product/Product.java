package br.com.zup.ecommerce.product;

import br.com.zup.ecommerce.category.Category;
import br.com.zup.ecommerce.user.User;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<ProductCharacteristic> characteristics = new HashSet<>();

    @OneToMany(mappedBy = "reviewedProduct")
    private List<ProductReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductQuestion> questions = new ArrayList<>();

    @Deprecated
    public Product() {

    }

    public Product(String name, BigDecimal price, Long availableQuantity, String description, Category category, User user) {
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

    public Long getId() {
        Assert.state(id != null, "O objeto não possui ID. Verifique se é um objeto TRANSIENT");
        return id;
    }

    public User getOwner() {
        return user;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<ProductImage> getImages() {
        return images;
    }

    public Set<ProductCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAverageRate() {
        if(reviews.isEmpty()) throw new IllegalCallerException("O produto não tem nenhuma avaliacão para ser mensurada");

        BigDecimal total = reviews.stream().map(review -> BigDecimal.valueOf(review.getRate())).reduce(BigDecimal.ZERO, BigDecimal::add);
        return total.setScale(2).divide(BigDecimal.valueOf(reviews.size()), RoundingMode.HALF_EVEN);
    }

    public int getTotalReviews() {
        return reviews.size();
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    public List<ProductQuestion> getQuestions() {
        return questions;
    }
}
