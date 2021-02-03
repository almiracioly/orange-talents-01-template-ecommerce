package br.com.zup.ecommerce.product;

import br.com.zup.ecommerce.user.User;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Deprecated
    public Product() {

    }

    public Product(String name, BigDecimal price, Long availableQuantity, String description, User user) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.user = user;
    }
}
