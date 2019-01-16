package com.aged.supercal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Individual_result extends AppCompatActivity {

    private TextView individual_after_income;
    private TextView individual_before_income;
    private TextView individual_total_tax;

    public void InitView(){
        individual_after_income = (TextView) findViewById(R.id.individual_after_income);
        individual_before_income = (TextView) findViewById(R.id.individual_before_income);
        individual_total_tax= (TextView) findViewById(R.id.individual_total_tax);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_result);

        InitView();  //初始化


        //接受值
        Bundle bunde = this.getIntent().getExtras();
        String individualafterincome=bunde.getString("individualafterincome");
        String individualbeforeincome=bunde.getString("individualbeforeincome");
        String individualtotaltax=bunde.getString("individualtotaltax");


        individual_after_income.setText(individualafterincome);
        individual_before_income.setText(individualbeforeincome);
        individual_total_tax.setText(individualtotaltax);

    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onPause();
    }
}
