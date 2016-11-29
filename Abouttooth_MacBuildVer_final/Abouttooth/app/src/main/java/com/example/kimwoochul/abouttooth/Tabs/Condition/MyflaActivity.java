package com.example.kimwoochul.abouttooth.Tabs.Condition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kimwoochul.abouttooth.R;

/**
 * Created by Jh on 2016-11-15.
 */

public class MyflaActivity extends AppCompatActivity {
    Button btn_cancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfla);
        btn_cancel = (Button) findViewById(R.id.btn_myfla_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}