package com.aged.supercal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Individual_operation extends AppCompatActivity implements View.OnClickListener{

    Button Individual_btn;
    private TextView Individual_income;
    private TextView textView_rate;

    public void Intitview(){
        Individual_btn = (Button) findViewById(R.id.Individual_btn);  //计算按钮
        Individual_income=(TextView) findViewById(R.id.Individual_income);
        Individual_btn.setOnClickListener(this);
        //税率表 可忽略
        textView_rate = (TextView)findViewById(R.id.textView_rate);
        textView_rate.setOnClickListener(new TextViewListener());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_operation);

        Intitview();  //初始化
    }

    public void onClick(View v){
        if(Individual_income.getText().toString().equals("")){
            Toast.makeText(Individual_operation.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else{
            double Individualincome=Double.parseDouble(Individual_income.getText().toString());

            //计算个体户利息
            double result=0;   //保存工资利息
            if(Individualincome<=15000)  result=Individualincome*0.05;   //不超过15000
            if(15000<Individualincome && Individualincome<=30000)   result=Individualincome*0.1;    //3000-12000
            if(30000<Individualincome && Individualincome<=60000)   result=Individualincome*0.2;   //12000-25000
            if(60000<Individualincome && Individualincome<=100000)  result=Individualincome*0.3;   //25000-35000
            if(Individualincome>100000)                             result=Individualincome*0.35;

            //跳转结果界面
            Intent i=new Intent(Individual_operation.this,Individual_result.class);

            //传值
            Bundle bundle = new Bundle();
            bundle.putString("individualafterincome", String.valueOf(Individualincome-result));
            bundle.putString("individualbeforeincome", String.valueOf(Individualincome));
            bundle.putString("individualtotaltax", String.valueOf(result));
            i.putExtras(bundle);

            startActivity(i);
        }
    }

    class TextViewListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Individual_operation.this,RatePersonActivity.class);
            startActivity(intent);
        }
    }
}
