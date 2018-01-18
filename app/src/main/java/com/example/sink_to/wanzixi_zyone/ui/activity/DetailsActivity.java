package com.example.sink_to.wanzixi_zyone.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sink_to.wanzixi_zyone.R;

public class DetailsActivity extends AppCompatActivity {


    private ImageView iv_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);




        Intent intent = getIntent();
        String img = intent.getStringExtra("xxx");
        String text = intent.getStringExtra("yyy");


        iv_intent = findViewById(R.id.lv_intent);


        Toast.makeText(this, "xxx"+"yyy", Toast.LENGTH_SHORT).show();
    }
}
