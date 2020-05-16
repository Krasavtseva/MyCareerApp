package com.example.tesster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.start_btn);

        startBtn.setOnClickListener(v -> {
            Intent categoryIntent = new Intent(getApplicationContext(), CategoriesActivity.class);
            startActivity(categoryIntent);
        });
    }
}
