package com.example.secure_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.secure_demo.security.DecryptUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {
    private Button btn_str;
    private Button btn_txt;
    private Button btn_reg;
    private Button btn_find;
    private TextView contentTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"f91e188d656dcbaaeb6ba84f46b4f0e6");
        btn_str = (Button)findViewById(R.id.btn_str);
        btn_reg=findViewById(R.id.btn_reg);
        btn_find=findViewById(R.id.btn_find);
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,findActivity.class);
                startActivity(intent);
            }
        });
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_str.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StrActivity.class);
                startActivity(intent);
            }
        });

        btn_txt = (Button)findViewById(R.id.btn_txt);
        btn_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TxtActivity.class);
                startActivity(intent);
            }
        });


    }


}