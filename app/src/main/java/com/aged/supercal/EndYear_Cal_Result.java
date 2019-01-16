package com.aged.supercal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class EndYear_Cal_Result extends AppCompatActivity {

    private TextView aftertax_income;
    private TextView beforetax_income;
    private TextView total_tax;

    public void InitView(){
        aftertax_income = (TextView) findViewById(R.id.aftertax_income);
        beforetax_income = (TextView) findViewById(R.id.beforetax_income);
        total_tax= (TextView) findViewById(R.id.total_tax);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_year_cal_result);

        InitView();  //初始化函数

        //接受值
        Bundle bunde = this.getIntent().getExtras();
        String after_imcome=bunde.getString("after_income");
        String before_imcome=bunde.getString("before_income");
        String whole_tax=bunde.getString("whole_tax");


        aftertax_income.setText(after_imcome);
        beforetax_income.setText(before_imcome);
        total_tax.setText(whole_tax);

       /* Toast.makeText(EndYear_Cal_Result.this,after_imcome,Toast.LENGTH_SHORT).show();
        Toast.makeText(EndYear_Cal_Result.this,before_imcome,Toast.LENGTH_SHORT).show();
        Toast.makeText(EndYear_Cal_Result.this,whole_tax,Toast.LENGTH_SHORT).show();*/

    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onPause();
    }
}
