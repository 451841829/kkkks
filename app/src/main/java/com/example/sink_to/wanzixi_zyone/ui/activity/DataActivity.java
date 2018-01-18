package com.example.sink_to.wanzixi_zyone.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sink_to.wanzixi_zyone.R;
import com.example.sink_to.wanzixi_zyone.data.bean.MyData;
import com.example.sink_to.wanzixi_zyone.ui.adapter.MyAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recy;

    private String mUrl = "https://api.kaishustory.com/homeservice/layout/content?userid=&layoutid=4&page_no=2&page_size=20";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        private MyAdapter myAdapter;

        @Override
        public void handleMessage(Message msg) {
            String jsonStr = (String) msg.obj;
            Gson gson = new Gson();
            MyData myData = gson.fromJson(jsonStr, MyData.class);
            final List<MyData.ResultBean.ListBean> list = myData.getResult().getList();
            myAdapter = new MyAdapter(list);
            recy.setAdapter(myAdapter);
            myAdapter.setOnItemListener(new MyAdapter.OnItemClick() {
                @Override
                public void setOnItem(View v, int position) {
                    Intent intent = new Intent(DataActivity.this,DetailsActivity.class);
                    intent.putExtra("xxx",list.get(position).getCoverurl());
                    intent.putExtra("yyy",list.get(position).getContenttype());
                    startActivity(intent);

                    finish();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initView();
        initOk();
    }

    private void initView() {
        recy = findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DataActivity.this);
        recy.setLayoutManager(linearLayoutManager);
    }

    private void initOk() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(mUrl).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
               mHandler.obtainMessage(1,string).sendToTarget();

            }
        });
    }
}
