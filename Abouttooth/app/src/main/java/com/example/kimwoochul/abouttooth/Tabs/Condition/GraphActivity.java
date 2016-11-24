package com.example.kimwoochul.abouttooth.Tabs.Condition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kimwoochul.abouttooth.R;

/**
 * Created by Jh on 2016-11-15.
 */
public class GraphActivity extends AppCompatActivity {
    Button btn_cancel;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        btn_cancel=(Button) findViewById(R.id.btn_graph_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
