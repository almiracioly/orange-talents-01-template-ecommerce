package br.com.zup.ecommerce.product;

import br.com.zup.ecommerce.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_questions")
public class ProductQuestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne
    private User author;

    public ProductQuestion(String title, Product product,  User questionAuthor) {
        this.title = title;
        this.product = product;
        author = questionAuthor;
    }

    public String getTitle() {
        return title;
    }

}
