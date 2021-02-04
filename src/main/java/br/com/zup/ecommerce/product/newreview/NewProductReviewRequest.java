package br.com.zup.ecommerce.product.newreview;

import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.product.ProductReview;
import br.com.zup.ecommerce.user.User;

import javax.validation.constraints.*;

public class NewProductReviewRequest {
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rate;

    @NotBlank
    private String title;

    @NotBlank
    @Size(min = 1, max = 500)
    private String description;

    public NewProductReviewRequest(@NotNull @Min(1) @Max(5) Integer rate, @NotBlank String title, @NotBlank String description) {
        this.rate = rate;
        this.title = title;
        this.description = description;
    }

    public ProductReview toModel(Product reviewedProduct, User reviewAuthor) {
        return new ProductReview(rate, title, description, reviewedProduct, reviewAuthor);
    }
}
