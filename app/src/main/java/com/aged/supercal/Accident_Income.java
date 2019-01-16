package com.aged.supercal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.content.Intent;
import android.widget.Toast;
import java.text.DecimalFormat;

public class Accident_Income extends AppCompatActivity implements View.OnClickListener{

    Button accident_btn;
    private TextView accident_income;
    private RadioButton accident_classfy;
    private RadioButton accident_classfy_qita;

    public void Intitview(){
        accident_btn = (Button) findViewById(R.id.accident_btn);  //计算按钮
        accident_income=(TextView) findViewById(R.id.accident_income);
        accident_classfy=(RadioButton) findViewById(R.id.accident_classfy);
        accident_classfy_qita=(RadioButton) findViewById(R.id.accident_classfy_qita);

        accident_btn.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_income);

        Intitview();   //调用初始化函数
    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onPause();
    }

    public void onClick(View v) {

        if (accident_income.getText().toString().equals("")) {
            Toast.makeText(Accident_Income.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        }else{
            double eaccidentincome = Double.parseDouble(accident_income.getText().toString());
            double result = 0;  //保存结果

            //跳转结果界面
            Intent i = new Intent(Accident_Income.this, Accident_result.class);

            //中奖收入
            if (accident_classfy.isChecked()) {
                if (eaccidentincome <= 10000) result = 0;
                if (eaccidentincome > 10000) result = eaccidentincome * 0.2;

                //传值
                DecimalFormat df=new DecimalFormat( "###############0.00 ");
                //传值
                Bundle bundle = new Bundle();
                bundle.putString("accident_after_income", df.format(eaccidentincome - result));
                bundle.putString("accident_before_income", df.format(eaccidentincome));
                bundle.putString("accident_total_tax", df.format(result));
                i.putExtras(bundle);

                startActivity(i);
            }
            //其他
            else if (accident_classfy_qita.isChecked()) {
                result = eaccidentincome * 0.2;
                DecimalFormat df=new DecimalFormat( "###############0.00 ");

                //传值
                Bundle bundle = new Bundle();
                bundle.putString("accident_after_income", df.format(eaccidentincome - result));
                bundle.putString("accident_before_income", df.format(eaccidentincome));
                bundle.putString("accident_total_tax", df.format(result));
                i.putExtras(bundle);

                //  Toast.makeText(Accident_Income.this, df.format(eaccidentincome - result), Toast.LENGTH_SHORT).show();

                startActivity(i);
            } else {
                Toast.makeText(Accident_Income.this, "请选择意外收入类型", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
