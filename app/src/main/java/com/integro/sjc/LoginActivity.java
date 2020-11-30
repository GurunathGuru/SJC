package com.integro.sjc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tvFaculty = findViewById(R.id.tvFaculty);
        TextView tvStudent = findViewById(R.id.tvStudent);
        TextView tvParent = findViewById(R.id.tvParent);
        TextView tvAdmission = findViewById(R.id.tvAdmission);

        tvFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtra("TAG", "https://sjc.linways.com/staff/");
                startActivity(intent);
            }
        });

        tvStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtra("TAG", "https://sjc.linways.com/student/");
                startActivity(intent);
            }
        });

        tvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtra("TAG", "https://sjc.linways.com/student/parent.php");
                startActivity(intent);
            }
        });

        tvAdmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtra("TAG", "https://sjc.linways.com/onlineapplication/");
                startActivity(intent);
            }
        });
    }
}