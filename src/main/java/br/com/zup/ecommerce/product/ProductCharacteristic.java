package br.com.zup.ecommerce.product;

import javax.persistence.*;

@Entity
@Table(name = "product_characteristics")
public class ProductCharacteristic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Deprecated
    public ProductCharacteristic() {

    }

    public ProductCharacteristic(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }
}
