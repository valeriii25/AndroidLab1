package com.example.androidlab1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String greeting = intent.getStringExtra("message");
        TextView greetingView = findViewById(R.id.greeting);
        greetingView.setText(greeting);

        Button send = findViewById(R.id.button);
        send.setOnClickListener(x -> {
            Intent result = new Intent();
            EditText nameField = findViewById(R.id.nameField);
            String name = nameField.getText().toString();
            if (name.isEmpty()) name = "user";
            result.putExtra("message", greeting + ", " + name + '!');
            setResult(RESULT_OK, result);
            finish();
        });
    }
}