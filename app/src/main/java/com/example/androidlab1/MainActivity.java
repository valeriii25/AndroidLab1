package com.example.androidlab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button send = findViewById(R.id.button);
        send.setOnClickListener(this::sendGreeting);
    }

    public void sendGreeting(View view) {
        Intent nameIntent = new Intent(this, NameActivity.class);
        EditText greetingField = findViewById(R.id.greetingField);
        String greeting = greetingField.getText().toString();
        if (greeting.isEmpty()) greeting = "Hello";
        nameIntent.putExtra("message", greeting);
        startActivityForResult(nameIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null)
            return;
        String greetingAndName = data.getStringExtra("message");
        TextView greetingAndNameView = findViewById(R.id.greetingAndName);
        greetingAndNameView.setText(greetingAndName);
    }
}