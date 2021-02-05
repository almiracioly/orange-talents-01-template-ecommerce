package br.com.zup.ecommerce.product.detail;

import br.com.zup.ecommerce.product.ProductReview;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductReviewResponse {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rate;
    @NotBlank
    private String authorEmail;

    public ProductReviewResponse(ProductReview review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.description = review.getDescription();
        this.rate = review.getRate();
        this.authorEmail = review.getAuthor().getEmail();
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

    public Integer getRate() {
        return rate;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }
}
