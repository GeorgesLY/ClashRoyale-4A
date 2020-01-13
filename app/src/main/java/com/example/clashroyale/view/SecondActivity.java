package com.example.clashroyale.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.clashroyale.R;

public class SecondActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView name = findViewById(R.id.firstLine);
        TextView description = findViewById(R.id.secondLine);
        ImageView imageView = findViewById(R.id.icon);

        Intent intent = getIntent();

        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
        Glide.with(getApplicationContext()).load(intent.getStringExtra("url")).into(imageView);

        setTitle("");
    }
}

