package com.example.vigenerecipher2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vigenerecipher2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'vigenerecipher2' library on application startup.
    static {
        System.loadLibrary("vigenerecipher2");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int myColor = Color.argb(128, 255, 0, 0); // RGBA color with 50% opacity, red: 255, green: 0, blue: 0

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;

        tv.setText(stringFromJNI());
        Button enterButton = findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passToVigenere = new Intent(MainActivity.this, vigenere.class);
                startActivity(passToVigenere);
            }
        });
    }

    /**
     * A native method that is implemented by the 'vigenerecipher2' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}