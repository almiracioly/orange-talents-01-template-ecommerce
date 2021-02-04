package br.com.zup.ecommerce.product.uploadimages;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Primary
public class FakeUploadImageService implements UploadImageService {
    @Override
    public Set<String> store(List<MultipartFile> images) {
        return images
                .stream()
                .map(image-> "http://imgbucket.io/"
                    .concat(image.getOriginalFilename()
                    .concat("-" + new Date().getTime()))).collect(Collectors.toSet());
    }
}
