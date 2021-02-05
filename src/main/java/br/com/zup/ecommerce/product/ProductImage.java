package br.com.zup.ecommerce.product;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String link;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Deprecated
    public ProductImage() {

    }

    public ProductImage(String imageLink, Product product) {
        this.link = imageLink;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
