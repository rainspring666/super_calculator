package com.aged.supercal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Drawf_result extends AppCompatActivity {

    private TextView drawf_after_income;
    private TextView drawf_total_tax;
    private TextView drawf_before_income;

    public void InitView(){
        drawf_after_income = (TextView) findViewById(R.id.drawf_after_income);
        drawf_total_tax= (TextView) findViewById(R.id.drawf_total_tax);
        drawf_before_income= (TextView) findViewById(R.id.drawf_before_income);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawf_result);

        InitView();

        //接受值
        Bundle bunde = this.getIntent().getExtras();
        String drawfafterincome=bunde.getString("drawfafterincome");
        String drawfbeforeincome=bunde.getString("drawfbeforeincome");
        String drawftotaltax=bunde.getString("drawftotaltax");


        drawf_after_income.setText(drawfafterincome);
        drawf_total_tax.setText(drawftotaltax);
        drawf_before_income.setText(drawfbeforeincome);
    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onPause();
    }
}
