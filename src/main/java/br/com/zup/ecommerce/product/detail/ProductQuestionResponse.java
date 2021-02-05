package br.com.zup.ecommerce.product.detail;

import br.com.zup.ecommerce.product.ProductQuestion;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ProductQuestionResponse {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String authorEmail;
    @NotNull
    private LocalDateTime sentOn;

    public ProductQuestionResponse(ProductQuestion question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.authorEmail = question.getAuthor().getEmail();
        this.sentOn = question.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public LocalDateTime getSentOn() {
        return sentOn;
    }
}
