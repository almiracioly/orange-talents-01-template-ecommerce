package br.com.zup.ecommerce.product.uploadimages;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface UploadImageService {
    Set<String> store(List<MultipartFile> images);
}
