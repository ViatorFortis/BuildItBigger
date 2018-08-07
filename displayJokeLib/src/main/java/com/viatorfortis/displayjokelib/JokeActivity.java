package com.viatorfortis.displayjokelib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(getString(R.string.joke_key) );

        if (joke != null) {
            TextView jokeTextView = findViewById(R.id.tv_joke);
            jokeTextView.setText(joke);
        } else {
            Toast.makeText(this, "Oh no! We're out of jokes.", Toast.LENGTH_SHORT).show();
        }

    }
}
