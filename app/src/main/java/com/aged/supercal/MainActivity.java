package com.aged.supercal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView textView_loan;//贷款计算
    private TextView textView_paf;//公积金

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_loan = (TextView)findViewById(R.id.textView_loan);
        textView_paf = (TextView)findViewById(R.id.textView5);
        textView_paf.setOnClickListener(new TextViewListener());
        textView_loan.setOnClickListener(new TextViewListener());

        ///////////////
        //计算器页面跳转------1-------
        TextView digital_cal = findViewById(R.id.digital_cal);
        digital_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Digital_calculate.class);
                startActivity(i);
            }});
        //月薪税收入跳转------4-------
        TextView month_salary= findViewById(R.id.month_salary);
        month_salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Month_salary.class);
                startActivity(i);
            }});

        //年终将收入页面跳转------5-------
        TextView end_year = findViewById(R.id.end_year);
        end_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,EndYear_Cal_Activity.class);
                startActivity(i);
            }});
        //劳务所得收入页面跳转----6-------
        TextView drawf_fee = findViewById(R.id.drawf_fee);
        drawf_fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Drawf_fee.class);
                startActivity(i);
            }});
        //意外收入页面跳转-------7-------
        TextView incident_income = findViewById(R.id.incident_income);
        incident_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Accident_Income.class);
                startActivity(i);
            }});
        //财产转让界面----------8-------
        TextView property_transfer= findViewById(R.id.property_transfer);
        property_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Property_transfer.class);
                startActivity(i);
            }});
        //个人经营页面----------9-------
        TextView Individual_operation= findViewById(R.id.Individual_operation);
        Individual_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Individual_operation.class);
                startActivity(i);
            }});

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction();
        }

    }
    //菜单处理
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_about:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    class TextViewListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.textView_loan){
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,LoanActivity.class);
                startActivity(intent);
            }else if(v.getId() == R.id.textView5);{
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoanActivity.class);
                startActivity(intent);
            }

        }


    }

}
