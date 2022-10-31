package com.swagata.scissors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class home_page extends AppCompatActivity {
ImageView menu;
LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        menu = findViewById(R.id.menuicon);
        layout=findViewById(R.id.layout);
        sidemenu sidemenu1 = new sidemenu();
        menu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,sidemenu1).commit();
                    }
                }
        );
    }
}