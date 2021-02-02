package br.com.zup.ecommerce.authentication;

public class BearerTokenResponse {
    private String token;
    private String type = "Bearer";

    public BearerTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
