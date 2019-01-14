package com.aged.supercal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import java.text.DecimalFormat;

public class EndYear_Cal_Activity extends AppCompatActivity implements View.OnClickListener{

    Button end_year_btn;
    private TextView end_year_money;
    private TextView end_month_income;


    //private StringBuilder pending = new StringBuilder();

    public void Intitview(){
        end_year_btn = (Button) findViewById(R.id.endyear_btn);  //计算按钮
        end_year_money=(TextView) findViewById(R.id.end_year_money);
        end_month_income=(TextView) findViewById(R.id.end_month_income);

        end_year_btn.setOnClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_year_cal);

        Intitview();   //调用初始化函数

    }

    public void onClick(View v){

        if(end_year_money.getText().toString().equals("")){
            Toast.makeText(EndYear_Cal_Activity.this, "年终奖输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(end_month_income.getText().toString().equals("")){
            Toast.makeText(EndYear_Cal_Activity.this, "奖金输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(!end_year_money.getText().toString().matches("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?") && !end_month_income.getText().toString().matches("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?")){  //判断是否全为数字
            Toast.makeText(EndYear_Cal_Activity.this, "输入中包含非数字字符",Toast.LENGTH_SHORT).show();
        }else{

            double endyearmoney=Double.parseDouble(end_year_money.getText().toString());
            double endmonthincome=Double.parseDouble(end_month_income.getText().toString());

            //计算工资利息
            double result_1=0;   //保存工资利息
            if(endmonthincome<=3000)  result_1=endmonthincome*0.03;   //不超过3000
            if(3000<endmonthincome && endmonthincome<=12000)   result_1=endmonthincome*0.10;    //3000-12000
            if(12000<endmonthincome && endmonthincome<=25000)   result_1=endmonthincome*0.20;   //12000-25000
            if(25000<endmonthincome && endmonthincome<=35000)  result_1=endmonthincome*0.25;   //25000-35000
            if(35000<endmonthincome && endmonthincome<=55000)  result_1=endmonthincome*0.30;   //35000-55000
            if(55000<endmonthincome && endmonthincome<=80000)  result_1=endmonthincome*0.35;   //55000-80000
            if(endmonthincome>80000)                           result_1=endmonthincome*0.45;   //大于80000


            //计算年终奖税率
            double result_2=0;  //保存年终奖个人所得税奖金

            if(endyearmoney<=5000)                               result_2=0;  //不超过3000
            if(endyearmoney/12<3000)                             result_2=endyearmoney*0.03;   //3000-12000
            if(3000<endyearmoney/12 && endyearmoney/12<=12000)   result_2=endyearmoney*0.10-210;   //3000-12000
            if(12000<endyearmoney/12 && endyearmoney/12<=25000)  result_2=endyearmoney*0.20-1410;   //12000-25000
            if(25000<endyearmoney/12 && endyearmoney/12<=35000)  result_2=endyearmoney*0.25-2660;   //25000-35000
            if(35000<endyearmoney/12 && endyearmoney/12<=55000)  result_2=endyearmoney*0.30-4410;   //35000-55000
            if(55000<endyearmoney/12 && endyearmoney/12<=80000)  result_2=endyearmoney*0.35-7160;   //55000-80000
            if(endyearmoney/12>80000)                            result_2=endyearmoney*0.45-15160;



            // Toast.makeText(EndYear_Cal_Activity.this, String.valueOf(result_1),Toast.LENGTH_SHORT).show();
            // Toast.makeText(EndYear_Cal_Activity.this, String.valueOf(result_2),Toast.LENGTH_SHORT).show();

            //跳转结果界面
            Intent i=new Intent(EndYear_Cal_Activity.this,EndYear_Cal_Result.class);

            DecimalFormat df=new DecimalFormat( "###############0.00 ");
            //传值
            Bundle bundle = new Bundle();
            bundle.putString("after_income", df.format(endyearmoney+endmonthincome-result_1-result_2));
            bundle.putString("before_income", df.format(endmonthincome));
            bundle.putString("whole_tax", df.format(result_1+result_2));
            i.putExtras(bundle);

            startActivity(i);

        }
    }

}
