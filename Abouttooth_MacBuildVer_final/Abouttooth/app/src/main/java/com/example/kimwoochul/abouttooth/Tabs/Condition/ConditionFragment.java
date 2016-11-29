package com.example.kimwoochul.abouttooth.Tabs.Condition;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kimwoochul.abouttooth.R;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.WHITE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConditionFragment extends Fragment

{
    ImageButton imgbtn;
    TextView txtm,txtv1,txtv3,txtv4;
    private static int count1=0;
    private static int count2=0;
    private static int count3=0;
    private static int count4=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_condition,null);
        imgbtn=(ImageButton)v.findViewById(R.id.conmainimgbtn);
        txtm=(TextView)v.findViewById(R.id.aaaaa);
        txtv4=(TextView)v.findViewById(R.id.contxtv4);
        txtv3=(TextView)v.findViewById(R.id.contxtv3);
        txtv1=(TextView)v.findViewById(R.id.contxtv1);
        txtv1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                count2=count3=count4=0;
                count1++;
                if(count1==2){

                    Intent intent = new Intent(getContext(),GraphActivity.class);
                    startActivity(intent);
                    count1=0;

                }
                else
                    imgbtn.setBackgroundResource(R.drawable.graphsample);
                txtm.setText("GRAPH");
                txtv4.setBackgroundColor(WHITE);
                txtv3.setBackgroundColor(WHITE);
                txtv1.setBackgroundColor(GRAY);
            }
        });

        txtv3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                count1=count2=count4=0;
                count3++;
                if(count3==2){
                    Intent intent = new Intent(getContext(),TimerActivity.class);
                    startActivity(intent);
                    count3=0;
                }
                else
                    imgbtn.setBackgroundResource(R.drawable.timersample);
                txtm.setText("TIMER");

                txtv4.setBackgroundColor(WHITE);
                txtv3.setBackgroundColor(GRAY);

                txtv1.setBackgroundColor(WHITE);
            }
        });
        txtv4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                count1=count3=count2=0;
                if(count4==2){
                    Intent intent = new Intent(getContext(),MyflaActivity.class);
                    startActivity(intent);
                    count4=0;
                }
                else
                    imgbtn.setBackgroundResource(R.drawable.myflasample);
                txtm.setText("MY FLA");

                txtv4.setBackgroundColor(GRAY);
                txtv3.setBackgroundColor(WHITE);
                txtv1.setBackgroundColor(WHITE);
            }
        });
        return v;
    }

}
