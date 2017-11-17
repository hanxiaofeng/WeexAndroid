package com.wangkeke.weexandroidhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WeexActivity.class);
//                intent.putExtra("url","http://192.168.61.99:8080/examples/listview.js");
                intent.putExtra("url","http://192.168.61.99:8080/examples/location.js");
                startActivity(intent);
            }
        });
    }
}
