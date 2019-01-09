package com.aged.supercal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView textView;//基本计算器
    private TextView textView_loan;//贷款计算

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView_loan = (TextView)findViewById(R.id.textView_loan);
        textView_loan.setOnClickListener(new TextViewListener());
        textView.setOnClickListener(new TextViewListener());
    }

    class TextViewListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.textView){
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,DigitalCalActivity.class);
                startActivity(intent);
            }else if(v.getId() == R.id.textView_loan){
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,LoanActivity.class);
                startActivity(intent);
            }

//            setContentView(R.layout.activity_digitalcal);
        }
    }

}
