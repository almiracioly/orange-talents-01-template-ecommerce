package br.com.zup.ecommerce.product.uploadimages;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UploadProductImagesRequest {

    @NotNull
    @Size(min = 1)
    private List<MultipartFile> images;

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

}
