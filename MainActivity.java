package com.example.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText questionInput;
    private EditText linkInput;
    private Button checkSimilarityButton;
    private TextView resultTextView;

    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        user = auth.getCurrentUser();
        questionInput = findViewById(R.id.questionInput);
        linkInput = findViewById(R.id.linkInput);
        checkSimilarityButton = findViewById(R.id.checkSimilarityButton);

        checkSimilarityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String question = questionInput.getText().toString();
                String quoraLink = linkInput.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://quora-sim-2.onrender.com/predict")  // Ensure the URL ends with a "/"
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                // Create a service interface for your API
                SimilarityService service = retrofit.create(SimilarityService.class);

                // Create a request object
                SimilarityRequest request = new SimilarityRequest(question, quoraLink, "rnd_rwoC2Dc6pov145EIMKfnHdrPdIy2");

                // Send the API request
                Call<SimilarityResponse> call = service.checkSimilarity(request);

                call.enqueue(new Callback<SimilarityResponse>() {
                    @Override
                    public void onResponse(Call<SimilarityResponse> call, Response<SimilarityResponse> response) {
                        if (response.isSuccessful()) {
                            double similarityScore = response.body().getSimilarityScore();
                            // Start the ResultActivity and pass the similarity score
                            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                            intent.putExtra("similarityScore", similarityScore);
                            startActivity(intent);
                        } else {
                            // Handle API error
                            // You can display an error message if needed
                        }
                    }

                    @Override
                    public void onFailure(Call<SimilarityResponse> call, Throwable t) {
                        // Handle network or other failures
                        // You can display an error message if needed
                    }
                });
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
