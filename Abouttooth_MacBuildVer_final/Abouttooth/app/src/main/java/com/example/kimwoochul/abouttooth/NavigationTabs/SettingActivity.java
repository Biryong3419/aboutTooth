package com.example.kimwoochul.abouttooth.NavigationTabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.kimwoochul.abouttooth.R;

public class SettingActivity extends AppCompatActivity
{
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = (Toolbar)findViewById(R.id.toolbar_setting);
        setSupportActionBar(toolbar);

        Button btn_cancle = (Button)findViewById(R.id.btn_setting_cancle);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}
