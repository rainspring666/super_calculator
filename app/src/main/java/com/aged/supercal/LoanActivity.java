package com.aged.supercal;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoanActivity extends AppCompatActivity {

    private EditText editText_money;
    private EditText editText_year;
    private EditText editText_rate;
    private TextView textView_paftable;
    private RadioButton radioButton_xi;
    private RadioButton radioButton_jin;
    private Button buttonCal;
    private int invest;
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
            invest = Integer.parseInt(money_str)*10000;
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
        inputWatch_money(editText_money);
        inputWatch_year(editText_year);
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

    public TextWatcher inputWatch_money(final EditText input) {
        return new TextWatcher() {
            private String outStr = ""; //这个值存储输入超过两位数时候显示的内容
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String edit=s.toString();
                if (edit.length()==5&&Integer.parseInt(edit)>=10){
                    outStr=edit;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String words = s.toString();
                //首先内容进行非空判断，空内容（""和null）不处理
                if (!TextUtils.isEmpty(words)) {
                    //1-100的正则验证
                    Pattern p = Pattern.compile("^(10000|[1-9]\\d|\\d|\\d|\\d)$");
                    Matcher m = p.matcher(words);
                    if (m.find() || ("").equals(words)) {
                        //这个时候输入的是合法范围内的值
                    } else {
                        if (words.length() > 5) {
                            //若输入不合规，且长度超过2位，继续输入只显示之前存储的outStr
                            input.setText(outStr);
                            //重置输入框内容后默认光标位置会回到索引0的地方，要改变光标位置
                            input.setSelection(5);
                        }
//                        ToastUtil.showToast("请输入范围在1-100之间的整数");
                        Toast.makeText(LoanActivity.this,"请输入范围在1-10000之间的整数",Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //这里的处理是不让输入0开头的值
                String words = s.toString();
                //首先内容进行非空判断，空内容（""和null）不处理
                if (!TextUtils.isEmpty(words)) {
                    if (Integer.parseInt(s.toString()) <= 0) {
                        input.setText("");
//                        ToastUtil.showToast("请输入范围在1-100之间的整数");
                    }
                }
            }
        };
    }

    public TextWatcher inputWatch_year(final EditText input) {

//        Toast.makeText(LoanActivity.this,"请输入1-30年",Toast.LENGTH_SHORT).show();
        return new TextWatcher() {
            private String outStr = ""; //这个值存储输入超过两位数时候显示的内容
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String edit=s.toString();
                if (edit.length()==2&&Integer.parseInt(edit)>=10){
                    outStr=edit;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String words = s.toString();
                //首先内容进行非空判断，空内容（""和null）不处理
                if (!TextUtils.isEmpty(words)) {
                    //1-100的正则验证
                    Pattern p = Pattern.compile("^([1-3]\\d|[0-3]\\d)$");
                    Matcher m = p.matcher(words);
                    if (m.find() || ("").equals(words)) {
                        //这个时候输入的是合法范围内的值
                    } else {
                        if (words.length() > 2) {
                            //若输入不合规，且长度超过2位，继续输入只显示之前存储的outStr
                            input.setText(outStr);
                            //重置输入框内容后默认光标位置会回到索引0的地方，要改变光标位置
                            input.setSelection(2);
                        }
                        Toast.makeText(LoanActivity.this,"请输入1-30年",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //这里的处理是不让输入0开头的值
                String words = s.toString();
                //首先内容进行非空判断，空内容（""和null）不处理
                if (!TextUtils.isEmpty(words)) {
                    if (Integer.parseInt(s.toString()) <= 0) {
                        input.setText("");
                        Toast.makeText(LoanActivity.this,"请输入1-30年",Toast.LENGTH_SHORT).show();                    }
                }
            }
        };
    }

}
