package com.example.login_register;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SimilarityService {
    @POST("checkSimilarityEndpoint") // Replace with your actual API endpoint
    Call<SimilarityResponse> checkSimilarity(@Body SimilarityRequest request);
}