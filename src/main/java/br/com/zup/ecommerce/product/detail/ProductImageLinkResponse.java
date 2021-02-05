package br.com.zup.ecommerce.product.detail;

import br.com.zup.ecommerce.product.ProductImage;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductImageLinkResponse {

    @NotNull
    private Long id;

    @NotBlank
    @URL
    private String link;

    public ProductImageLinkResponse(ProductImage image) {
        this.id = image.getId();
        this.link = image.getLink();
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
