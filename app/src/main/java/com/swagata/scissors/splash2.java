package com.swagata.scissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class splash2 extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> quotes(ArrayList<String> list){
        list.add("I loathe narcissism, but I approve of vanity.");
        list.add("You either know fashion or you don’t");
        list.add("One is never over-dressed or under-dressed with a Little Black Dress.");
        list.add("Style is a way to say who you are without having to speak.");
        list.add("Trendy is the last stage before tacky.");
        list.add("Fashion is like eating, you shouldn't stick to the same menu.");
        return list;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<String> list1=quotes(list);
        int index = new Random().nextInt(list1.size());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        tv1.setText(list1.get(index));
        tv2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), sign_up.class);
                        startActivity(intent);
                    }
                }
        );
        tv3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}