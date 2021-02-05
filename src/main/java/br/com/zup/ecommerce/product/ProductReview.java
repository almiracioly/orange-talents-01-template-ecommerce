package br.com.zup.ecommerce.product;

import br.com.zup.ecommerce.user.User;

import javax.persistence.*;

@Entity
@Table(name = "product_reviews")
public class ProductReview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(nullable = false)
    private Integer rate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product reviewedProduct;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Deprecated
    public ProductReview() {

    }

    public ProductReview(Integer rate, String title, String description, Product reviewedProduct, User reviewAuthor) {
        this.rate = rate;
        this.title = title;
        this.description = description;
        this.reviewedProduct = reviewedProduct;
        author = reviewAuthor;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getAuthor() {
        return author;
    }

    public Integer getRate() {
        return rate;
    }
}
