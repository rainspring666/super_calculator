package com.aged.supercal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Property_transfer_result extends AppCompatActivity {

    private TextView property_beforeamount;
    private TextView property_afteramount;
    private TextView property_totaltax;

    public void InitView(){
        property_beforeamount = (TextView) findViewById(R.id.property_beforeamount);
        property_afteramount = (TextView) findViewById(R.id.property_afteramount);
        property_totaltax= (TextView) findViewById(R.id.property_totaltax);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_transfer_result);

        InitView();

        //接受值
        Bundle bunde = this.getIntent().getExtras();
        String property_after_income=bunde.getString("property_after_income");
        String property_before_income=bunde.getString("property_before_income");
        String property_total_tax=bunde.getString("property_total_tax");


        property_beforeamount.setText(property_before_income);
        property_afteramount.setText(property_after_income);
        property_totaltax.setText(property_total_tax);
    }
}
