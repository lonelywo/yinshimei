package com.cuci.enticement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class JumpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String data = intent.getDataString(); // 接收到网页传过来的数据：scheme://data/xxx
        String[] split = data.split("data/");
        String param = split[1]; // 获取到网页传过来的参数
    }
}
