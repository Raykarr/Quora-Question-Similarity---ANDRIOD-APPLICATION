package com.example.login_register;

public class SimilarityRequest {
    private String question;
    private String quoraLink;
    private String apiKey;

    public SimilarityRequest(String question, String quoraLink, String apiKey) {
        this.question = question;
        this.quoraLink = quoraLink;
        this.apiKey = apiKey;
    }
}
