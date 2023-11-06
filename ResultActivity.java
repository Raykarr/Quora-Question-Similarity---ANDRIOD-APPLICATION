package com.example.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get the similarity score from the intent
        Intent intent = getIntent();
        double similarityScore = intent.getDoubleExtra("similarityScore", 0.0);

        // Display the similarity result
        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Similarity Score: " + similarityScore);
    }
}
