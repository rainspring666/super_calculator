package com.aged.supercal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Accident_result extends AppCompatActivity {

    private TextView accident_before_money;
    private TextView accident_after_money;
    private TextView accident_whole_tax;


    public void InitView(){
        accident_after_money = (TextView) findViewById(R.id.accident_after_money);
        accident_whole_tax= (TextView) findViewById(R.id.accident_whole_tax);
        accident_before_money= (TextView) findViewById(R.id.accident_before_money);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_result);

        InitView();

        //接受值
        Bundle bunde = this.getIntent().getExtras();
        String accident_after_income=bunde.getString("accident_after_income");
        String accident_before_income=bunde.getString("accident_before_income");
        String accident_total_tax=bunde.getString("accident_total_tax");


        accident_after_money.setText(accident_after_income);
        accident_before_money.setText(accident_before_income);
        accident_whole_tax.setText(accident_total_tax);
    }


}
