package com.aged.supercal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Property_transfer extends AppCompatActivity implements View.OnClickListener{

    private TextView property_total_amount;
    private TextView property_pri_amount;
    private TextView property_stable_amount;
    Button property_btn;


    public void Intitview(){
        property_btn = (Button) findViewById(R.id.property_btn);  //计算按钮
        property_total_amount=(TextView) findViewById(R.id.property_total_amount);
        property_pri_amount=(TextView) findViewById(R.id.property_pri_amount);
        property_stable_amount=(TextView) findViewById(R.id.property_stable_amount);

        property_btn.setOnClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_transfer);

        Intitview();   //调用初始化函数
    }

    public void onClick(View v){

        if(property_total_amount.getText().toString().equals("")){
            Toast.makeText(Property_transfer.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(property_pri_amount.getText().toString().equals("")){
            Toast.makeText(Property_transfer.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(property_stable_amount.getText().toString().equals("")){
            Toast.makeText(Property_transfer.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else{
            //计算结果
            double propertytotalamount=Double.parseDouble(property_total_amount.getText().toString());
            double propertypriamount=Double.parseDouble(property_pri_amount.getText().toString());
            double propertystableamount=Double.parseDouble(property_stable_amount.getText().toString());

            double result=(propertytotalamount-propertypriamount-propertystableamount)*0.2;


            //跳转结果界面
            Intent i=new Intent(Property_transfer.this,Property_transfer_result.class);

            //传值
            Bundle bundle = new Bundle();
            bundle.putString("property_after_income", String.valueOf(propertypriamount+propertypriamount-result));
            bundle.putString("property_before_income", String.valueOf(propertytotalamount));
            bundle.putString("property_total_tax", String.valueOf(result));
            i.putExtras(bundle);

            startActivity(i);
        }
    }
}
