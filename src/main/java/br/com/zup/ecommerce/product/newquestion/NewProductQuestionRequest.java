package br.com.zup.ecommerce.product.newquestion;

import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.product.ProductQuestion;
import br.com.zup.ecommerce.user.User;

import javax.validation.constraints.NotBlank;

public class NewProductQuestionRequest {
    @NotBlank
    private String title;

    public ProductQuestion toModel(User questionAuthor, Product product) {
        return new ProductQuestion(title, product, questionAuthor);
    }

    public String getTitle() {
        return title;
    }
}
