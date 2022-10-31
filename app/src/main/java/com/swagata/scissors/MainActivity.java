package com.swagata.scissors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media2.session.MediaController;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
TextView submit;
EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        submit = findViewById(R.id.tv_loginbutton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), signin_midpage.class);
//                startActivity(intent);
                final String mail = email.getText().toString();
                final String pass = password.getText().toString();
                if (mail.isEmpty() || pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter correct Email and Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("message");

                    myRef.setValue("Hello, World!");
                }
            }
        });

    }
}