package com.aged.supercal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class Drawf_fee extends AppCompatActivity implements View.OnClickListener{
    Button drawf_btn;
    private TextView drawf_fee;

    public void initView() {

        drawf_btn=(Button) findViewById(R.id.drawf_btn);
        drawf_fee=(TextView) findViewById(R.id.drawf_fee);
        drawf_btn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawf_fee);

        initView();
    }

    public void onClick(View v) {
        if (drawf_fee.getText().toString().equals("")) {   //判断是否为空
            Toast.makeText(Drawf_fee.this, "奖金输入不能为空", Toast.LENGTH_SHORT).show();
        } else {
            double drawffee = Double.parseDouble(drawf_fee.getText().toString());  //转化为double型

            //计算所交税率
            double tax = 0;   //保存计算结果
            if (drawffee <= 4000 && drawffee >800) tax = (drawffee - 800) ;   //收入小于4000
            if (drawffee > 4000) tax = drawffee * 0.8;
            if(tax<=20000)
                tax = tax * 0.2;
            else if(tax<=50000 && tax > 20000)
                tax = tax * 0.3 - 2000;
            else if(tax>50000)
                tax = tax * 0.4 -7000;

            //跳转结果界面
            Intent i = new Intent(Drawf_fee.this, Drawf_result.class);

            DecimalFormat df=new DecimalFormat( "###############0.00 ");

            //传值
            Bundle bundle = new Bundle();
            bundle.putString("drawfafterincome", df.format(drawffee-tax));
            bundle.putString("drawfbeforeincome", df.format(drawffee));
            bundle.putString("drawftotaltax", df.format(tax));
            i.putExtras(bundle);

            startActivity(i);

        }
    }
}
