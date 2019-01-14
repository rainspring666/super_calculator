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

            double year = Double.parseDouble(end_year_money.getText().toString());
            double month = Double.parseDouble(end_month_income.getText().toString());

            double taxMomey = 0;
            if(month >= 5000){
                double temp = year / 12;
                if(temp<=3000 && temp>=0)
                    taxMomey = year * 0.03;
                else if (temp<=12000 && temp>3000)
                    taxMomey = year * 0.1 - 210;
                else if(temp<=25000 && temp>12000)
                    taxMomey = year * 0.2 - 1410;
                else if(temp<=35000 && temp>25000)
                    taxMomey = year * 0.25 - 2660;
                else if(temp<=55000 && temp>35000)
                    taxMomey = year * 0.30 - 4410;
                else if(temp<=80000 && temp>55000)
                    taxMomey = year * 0.35 - 7160;
                else if(temp>80000)
                    taxMomey = year * 0.45 - 15160;
            }else{
                double newyear = year - (5000 - month );
                double temp = (newyear) / 12;
                if(temp<=3000 && temp>=0)
                    taxMomey = newyear * 0.03;
                else if (temp<=12000 && temp>3000)
                    taxMomey = newyear * 0.1 - 210;
                else if(temp<=25000 && temp>12000)
                    taxMomey = newyear * 0.2 - 1410;
                else if(temp<=35000 && temp>25000)
                    taxMomey = newyear * 0.25 - 2660;
                else if(temp<=55000 && temp>35000)
                    taxMomey = newyear * 0.30 - 4410;
                else if(temp<=80000 && temp>55000)
                    taxMomey = newyear * 0.35 - 7160;
                else if(temp>80000)
                    taxMomey = newyear * 0.45 - 15160;
            }

//             Toast.makeText(EndYear_Cal_Activity.this, String.valueOf(result_1),Toast.LENGTH_SHORT).show();
            // Toast.makeText(EndYear_Cal_Activity.this, String.valueOf(result_2),Toast.LENGTH_SHORT).show();

            //跳转结果界面
            Intent i=new Intent(EndYear_Cal_Activity.this,EndYear_Cal_Result.class);

            DecimalFormat df=new DecimalFormat( "###############0.00 ");
            //传值
            Bundle bundle = new Bundle();
            bundle.putString("after_income", df.format(year-taxMomey));
            bundle.putString("before_income", df.format(year));
            bundle.putString("whole_tax", df.format(taxMomey));
            i.putExtras(bundle);

            startActivity(i);

        }
    }

}
