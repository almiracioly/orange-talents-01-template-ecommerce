package br.com.zup.ecommerce.category;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Category parentCategory;

    @Deprecated
    public Category() {

    }

    public Category(String name, Category parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
