package br.com.zup.ecommerce.product.detail;

import br.com.zup.ecommerce.product.Product;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DetailedProductResponse {
    @NotNull
    private Long id;
    private Set<ProductImageLinkResponse> images = new HashSet<>();
    private String name;
    private BigDecimal price;
    private Set<ProductCharacteristicResponse> characteristics = new HashSet<>();
    private String description;
    private BigDecimal averageRate;
    private int totalReviews;
    private List<ProductReviewResponse> reviews = new ArrayList<>();
    private List<ProductQuestionResponse> questions = new ArrayList<>();

    public DetailedProductResponse(Product product) {
        // TODO: Otimizar retorno com paginacão das colecoes

        this.id = product.getId();
        this.images.addAll(productImageCollectionModelToResponse(product));
        this.name = product.getName();
        this.price = product.getPrice();
        this.characteristics.addAll(productCharacteristicCollectionModelToResponse(product));
        this.description = product.getDescription();
        this.averageRate = product.getReviews().isEmpty() ? BigDecimal.ZERO : product.getAverageRate();
        this.totalReviews = product.getTotalReviews();
        this.reviews.addAll(productReviewCollectionModelToResponse(product));
        this.questions.addAll(productQuestionCollectionModelToResponse(product));
    }

    private List<ProductQuestionResponse> productQuestionCollectionModelToResponse(Product product) {
        return product.getQuestions()
                .stream()
                .map(question -> new ProductQuestionResponse(question))
                .collect(Collectors.toList());
    }

    private List<ProductReviewResponse> productReviewCollectionModelToResponse(Product product) {
        return product.getReviews()
                .stream()
                .map(review -> new ProductReviewResponse(review))
                .collect(Collectors.toList());
    }

    private Set<ProductCharacteristicResponse> productCharacteristicCollectionModelToResponse(Product product) {
        return product.getCharacteristics()
                .stream()
                .map(characteristic -> new ProductCharacteristicResponse(characteristic))
                .collect(Collectors.toSet());
    }

    private Set<ProductImageLinkResponse> productImageCollectionModelToResponse(Product product) {
        return product.getImages()
                .stream()
                .map(image -> new ProductImageLinkResponse(image))
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public Set<ProductImageLinkResponse> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<ProductCharacteristicResponse> getCharacteristics() {
        return characteristics;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAverageRate() {
        return averageRate;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public List<ProductReviewResponse> getReviews() {
        return reviews;
    }

    public List<ProductQuestionResponse> getQuestions() {
        return questions;
    }
}
