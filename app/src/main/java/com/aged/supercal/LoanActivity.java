package com.aged.supercal;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aged.supercal.Tools.AverageCapital;
import com.aged.supercal.Tools.AverageCapitalPlusInterest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class LoanActivity extends AppCompatActivity {

    private EditText editText_money;
    private EditText editText_year;
    private EditText editText_rate;
    private TextView textView_paftable;
    private RadioButton radioButton_xi;
    private RadioButton radioButton_jin;
    private Button buttonCal;
    private double invest;
    private int year;
    private double yearRate;
    private int totalMonth;
    private int state = 0;//计算方式
    private RadioGroup radioGroup ;

    private AverageCapital benjin = new AverageCapital();
    private AverageCapitalPlusInterest benxi = new AverageCapitalPlusInterest();

    public void initData(){
        String money_str = editText_money.getText().toString().trim();
        String year_str = editText_year.getText().toString().trim();
        String rate_str = editText_rate.getText().toString().trim();

        if((money_str.equals("")||year_str.equals("")||rate_str.equals(""))){
            Toast.makeText(LoanActivity.this,"请输入需要数据",Toast.LENGTH_SHORT).show();
        }else{
            yearRate = Double.parseDouble(rate_str)/100.00;
            invest = Double.parseDouble(money_str)*10000;
            year = Integer.parseInt(year_str);
            totalMonth = 12* year;

        }

    }

    //开始计算贷款细则
    public void calLoan_xi(){
        BigDecimal totalRestxi = benxi.getInterestCount(invest,yearRate,totalMonth).setScale(2, BigDecimal.ROUND_DOWN);
        BigDecimal totalMoneyxi = benxi.getPrincipalInterestCount(invest,yearRate,totalMonth).setScale(2, BigDecimal.ROUND_DOWN);
        //每月剩余贷款金额
        Map<Integer,BigDecimal> map_PerMonthLeftPrin
                = benxi.getLeftInvest(invest,yearRate,totalMonth);
        //每月利息
        Map<Integer,BigDecimal> map_PerMonthRest
                = benxi.getPerMonthInterest(invest,yearRate,totalMonth);
        //每月归还本金
        Map<Integer,BigDecimal>  map_getPerMonthPrincipal
                = benxi.getPerMonthPrincipal(invest,yearRate,totalMonth);
        //每月归还的本金+利息（月供）
        BigDecimal perMonPrin = benxi.getPerMonthPrincipalInterest(invest,yearRate,totalMonth).setScale(2, BigDecimal.ROUND_DOWN);

//        Toast.makeText(LoanActivity.this,totalMoney+" "+totalRest,Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(LoanActivity.this,LoanResultActivity.class);
        intent.putExtra("fangshi",1);
        intent.putExtra("totalRest",totalRestxi+"元");
        intent.putExtra("totalMoney",totalMoneyxi+"元");
        intent.putExtra("yearRate",yearRate*100+""+"%");
        intent.putExtra("year",year+"");
        intent.putExtra("invest",(invest/10000)+"万元");
        intent.putExtra("perMonPrin",perMonPrin+"");//本息不变
//        剩余贷款金额;每月利息；月供
        intent.putExtra("map1",(Serializable)map_PerMonthLeftPrin);//每月剩余贷款金额
        intent.putExtra("map2",(Serializable)map_PerMonthRest);//每月利息
        intent.putExtra("map3",(Serializable)map_getPerMonthPrincipal);//每月本金

        startActivity(intent);//启动Activity
//        finish();


    }

    public void calLoan_jin(){
        BigDecimal totalRest = benjin.getTotalRest(invest,yearRate,totalMonth).setScale(2,BigDecimal.ROUND_DOWN);;
        BigDecimal totalMoney = benjin.getTotalMoney(invest,yearRate,totalMonth).setScale(2,BigDecimal.ROUND_DOWN);;
        //每月剩余贷款金额
        Map<Integer,BigDecimal> map_PerMonthLeftPrin = benjin.getLeftInvest(invest,totalMonth);
        //每月利息
        Map<Integer,BigDecimal> map_PerMonthRest = benjin.getPerMonthRest(invest,yearRate,totalMonth);
        //每月归还的本金+利息（月供）
        Map<Integer,BigDecimal>  map_PerMonthPrincipalInterest = benjin.getPerMonthPrincipalInterest(invest,yearRate,totalMonth);
        //每月归还本金
        BigDecimal perMonPrin = benjin.getPerMonthPrincipal(invest,totalMonth).setScale(2,BigDecimal.ROUND_DOWN);

//        Toast.makeText(LoanActivity.this,totalMoney+" "+totalRest,Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(LoanActivity.this,LoanResultActivity.class);
        intent.putExtra("fangshi",2);
        intent.putExtra("totalRest",totalRest+"元");
        intent.putExtra("totalMoney",totalMoney+"元");
        intent.putExtra("yearRate",yearRate*100+""+"%");
        intent.putExtra("year",year+"");
        intent.putExtra("invest",(invest/10000)+"万元");
        intent.putExtra("perMonPrin",perMonPrin+"");
        intent.putExtra("map1",(Serializable)map_PerMonthLeftPrin);//每月剩余贷款金额
        intent.putExtra("map2",(Serializable)map_PerMonthRest);//每月利息
        intent.putExtra("map3",(Serializable)map_PerMonthPrincipalInterest);//每月月供

        startActivity(intent);//启动Activity
//        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        textView_paftable = (TextView)findViewById(R.id.textView15);
        textView_paftable.setOnClickListener(new TextViewListener());
        textView_paftable.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线

        editText_money = (EditText)findViewById(R.id.editText_money);
        editText_year = (EditText)findViewById(R.id.editText_year);
        editText_rate = (EditText)findViewById(R.id.editText_rate);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroupId);
        radioButton_xi = (RadioButton)findViewById(R.id.radioButton3);
        radioButton_jin = (RadioButton)findViewById(R.id.radioButton4);
        buttonCal = (Button)findViewById(R.id.button2);

        //监听器
        buttonCal.setOnClickListener(new ButtonClickListener());
        radioGroup.setOnCheckedChangeListener(new RadioGroupListener());

        editText_rate.addTextChangedListener(new TextWatcher() {
            boolean deleteLastChar;// 是否需要删除末尾
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    // 如果点后面有超过三位数值,则删掉最后一位
                    int length = s.length() - s.toString().lastIndexOf(".");
                    // 说明后面有三位数值
                    deleteLastChar = length >= 4;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s == null) {
                    return;
                }
                if (deleteLastChar) {
                    // 设置新的截取的字符串
                    editText_rate.setText(s.toString().substring(0, s.toString().length() - 1));
                    // 光标强制到末尾
                    editText_rate.setSelection(editText_rate.getText().length());
                }
                // 以小数点开头，前面自动加上 "0"
                if (s.toString().startsWith(".")) {
                    editText_rate.setText("0" + s);
                    editText_rate.setSelection(editText_rate.getText().length());
                }
            }
        });
        editText_rate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText editText1 = (EditText) v;
                // 以小数点结尾，去掉小数点
                if (!hasFocus && editText1.getText() != null && editText1.getText().toString().endsWith(".")) {
                    editText_rate.setText(editText1.getText().subSequence(0, editText1.getText().length() - 1));
                    editText_rate.setSelection(editText_rate.getText().length());
                }
            }
        });


        editText_money.addTextChangedListener(new TextWatcher() {
            boolean deleteLastChar;// 是否需要删除末尾
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    // 如果点后面有超过三位数值,则删掉最后一位
                    int length = s.length() - s.toString().lastIndexOf(".");
                    // 说明后面有三位数值
                    deleteLastChar = length >= 4;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s == null) {
                    return;
                }
                if (deleteLastChar) {
                    // 设置新的截取的字符串
                    editText_money.setText(s.toString().substring(0, s.toString().length() - 1));
                    // 光标强制到末尾
                    editText_money.setSelection(editText_money.getText().length());
                }
                // 以小数点开头，前面自动加上 "0"
                if (s.toString().startsWith(".")) {
                    editText_money.setText("0" + s);
                    editText_money.setSelection(editText_money.getText().length());
                }
            }
        });
        editText_money.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText editText1 = (EditText) v;
                // 以小数点结尾，去掉小数点
                if (!hasFocus && editText1.getText() != null && editText1.getText().toString().endsWith(".")) {
                    editText_money.setText(editText1.getText().subSequence(0, editText1.getText().length() - 1));
                    editText_money.setSelection(editText_money.getText().length());
                }
            }
        });

    }

    class RadioGroupListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {

            if(id == radioButton_xi.getId()){
                state = 1;
            }else if(id == radioButton_jin.getId()){
                state = 2;
            }
        }
    }

    class TextViewListener implements View.OnClickListener{
        @Override
        public void onClick(View v){

                Intent intent = new Intent();
                intent.setClass(LoanActivity.this,RateActivity.class);
                startActivity(intent);
        }
    }

    class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            initData();

            if(v.getId() == R.id.textView15){
                Intent intent = new Intent();
                intent.setClass(LoanActivity.this,RateActivity.class);
                startActivity(intent);
            }

            if(state == 1){
                calLoan_xi();
            }else if(state == 2){
                calLoan_jin();
            }else{
//                Toast.makeText(LoanActivity.this,totalMoney+"",Toast.LENGTH_SHORT).show();
                Toast.makeText(LoanActivity.this,"请选择计算方式",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
