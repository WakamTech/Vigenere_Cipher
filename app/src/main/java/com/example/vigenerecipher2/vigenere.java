package com.example.vigenerecipher2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vigenerecipher2.databinding.ActivityMainBinding;
import com.example.vigenerecipher2.databinding.ActivityVigenereBinding;

public class vigenere extends AppCompatActivity {
    private ActivityVigenereBinding binding;
    private String key, mess, encryptedMess, decryptedMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVigenereBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText keyField = findViewById(R.id.keyField), messField = findViewById(R.id.messField);
        TextView resultField = findViewById(R.id.resultField);
        keyField.getBackground().setAlpha(86);
        resultField.getBackground().setAlpha(86);
        messField.getBackground().setAlpha(86);

        EditText keyText = binding.keyField, messText = binding.messField;
        Button encryptButton = binding.encryptButton, decryptButton = binding.decryptButton;
        TextView resultText = binding.resultField;

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!keyText.getText().toString().isEmpty() & !messText.getText().toString().isEmpty()){
                    key = keyText.getText().toString();
                    mess = messText.getText().toString();
                    String o_key = encryptMessage(mess, key);
                    resultText.setText(o_key);
                }
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!keyText.getText().toString().isEmpty() & !messText.getText().toString().isEmpty()){
                    key = keyText.getText().toString();
                    mess = messText.getText().toString();
                    String o_key = dencryptMessage(mess, key);
                    resultText.setText(o_key);
                }
            }
        });
    }
    public native String encryptMessage(String message, String key);
    public native String dencryptMessage(String message, String key);
}