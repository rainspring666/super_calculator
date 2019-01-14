package com.aged.supercal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            double result = 0;   //保存计算结果
            if (drawffee <= 4000) result = (drawffee - 800) * 0.2 * (1 - 0.3);   //收入小于4000
            if (drawffee > 4000) result = drawffee * (1 - 0.2) * 0.2 * (1 - 0.3);   //收入小于4000

            //跳转结果界面
            Intent i = new Intent(Drawf_fee.this, Drawf_result.class);

            //传值
            Bundle bundle = new Bundle();
            bundle.putString("drawfafterincome", String.valueOf(drawffee-result));
            bundle.putString("drawfbeforeincome", String.valueOf(drawffee));
            bundle.putString("drawftotaltax", String.valueOf(result));
            i.putExtras(bundle);

            startActivity(i);

        }
    }
}
