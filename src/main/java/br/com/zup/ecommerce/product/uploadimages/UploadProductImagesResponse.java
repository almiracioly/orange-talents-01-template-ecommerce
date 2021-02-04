package br.com.zup.ecommerce.product.uploadimages;

import java.util.Set;

public class UploadProductImagesResponse {
    Set<String> imageLinks;

    public UploadProductImagesResponse(Set<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    public Set<String> getImageLinks() {
        return imageLinks;
    }
}
