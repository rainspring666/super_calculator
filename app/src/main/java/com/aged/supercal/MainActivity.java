package com.aged.supercal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import android.app.AlertDialog;
public class MainActivity extends AppCompatActivity {

    private TextView textView_loan;//贷款计算
    private TextView textView_paf;//公积金
    private TextView textView12;//10
    private TextView textView11;//11
    private TextView textView3;//12

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView11 = (TextView)findViewById(R.id.textView11);
        textView12 = (TextView)findViewById(R.id.textView12);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView_loan = (TextView)findViewById(R.id.textView_loan);
        textView_paf = (TextView)findViewById(R.id.textView5);
        textView_paf.setOnClickListener(new TextViewListener());
        textView_loan.setOnClickListener(new TextViewListener());
        textView11.setOnClickListener(new TextViewListener());
        textView12.setOnClickListener(new TextViewListener());
        textView3.setOnClickListener(new TextViewListener());

        ///////////////
        //计算器页面跳转------1-------
        TextView digital_cal = findViewById(R.id.digital_cal);
        digital_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Digital_calculate.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }});
        //月薪税收入跳转------4-------
        TextView month_salary= findViewById(R.id.month_salary);
        month_salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Month_salary.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }});

        //年终将收入页面跳转------5-------
        TextView end_year = findViewById(R.id.end_year);
        end_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,EndYear_Cal_Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }});
        //劳务所得收入页面跳转----6-------
        TextView drawf_fee = findViewById(R.id.drawf_fee);
        drawf_fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Drawf_fee.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }});
        //意外收入页面跳转-------7-------
        TextView incident_income = findViewById(R.id.incident_income);
        incident_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Accident_Income.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }});
        //财产转让界面----------8-------
        TextView property_transfer= findViewById(R.id.property_transfer);
        property_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Property_transfer.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }});
        //个人经营页面----------9-------
        TextView Individual_operation= findViewById(R.id.Individual_operation);
        Individual_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,Individual_operation.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
            Intent intent=new Intent();
            AlertDialog alertDialog1 = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("提示")//标题
                    .setMessage("此功能开发中")//内容
                    .setIcon(R.mipmap.ic_launcher1)//图标
                    .create();

            switch (v.getId()){
                case R.id.textView_loan:
                    intent.setClass(MainActivity.this,LoanActivity.class);
                    intent.putExtra("flag", 1);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    break;
                case R.id.textView5:
                    intent.setClass(MainActivity.this,LoanActivity.class);
                    intent.putExtra("flag", 2);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    break;
                case R.id.textView12:
                    alertDialog1.show();
                    break;
                case R.id.textView11:
                    alertDialog1.show();
                    break;
                case R.id.textView3:
                    alertDialog1.show();
                    break;
            }

        }


    }

}
