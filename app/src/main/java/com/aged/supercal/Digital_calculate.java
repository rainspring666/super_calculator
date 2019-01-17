package com.aged.supercal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aged.supercal.Tools.InfixInToDuffix;

import java.util.Arrays;


public class Digital_calculate extends AppCompatActivity implements View.OnClickListener{

    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_point; //小数点
    Button btn_clear; //清除
    Button btn_del;   //删除
    Button btn_plus;
    Button btn_minus;
    Button btn_multiply;
    Button btn_divide;
    Button btn_equal;
    Button btn_left;
    Button btn_right;
    private TextView et_input;
    private StringBuilder pending = new StringBuilder();  //保存结果值
    private StringBuilder pending_clean = new StringBuilder();   //保存清0值
    private boolean flag=false;  //状态值

    public void initView(){
        btn_0=(Button)findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        et_input = (TextView) findViewById(R.id.et_input);
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_right = (Button) findViewById(R.id.btn_right);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_calculate);

        initView();   //调用初始化函数

    }



    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onPause();
    }

    public void onClick(View v) {

        int last = 0;
        if (pending.length() != 0) {
            last = pending.codePointAt(pending.length() - 1);
        }
        switch (v.getId()) {
            case R.id.btn_0:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge3()){   //判断是否在可以输入为0
                    pending = pending.append("0");
                    et_input.setText(pending);
                    if(flag==true) {
                        pending=pending.delete(0,pending.length());
                        flag=false;
                    }
                }
                break;
            case R.id.btn_1:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge4()){
                    pending = pending.append("1");
                    et_input.setText(pending);
                }

                break;
            case R.id.btn_2:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge4()){
                    pending = pending.append("2");
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_3:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge4()){
                    pending = pending.append("3");
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_4:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge4()){
                    pending = pending.append("4");
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_5:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge4()){
                    pending = pending.append("5");
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_6:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge4()){
                    pending = pending.append("6");
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_7:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge4()){
                    pending = pending.append("7");
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_8:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge4()){
                    pending = pending.append("8");
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_9:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge4()){
                    pending = pending.append("9");
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_plus:
                flag=false;
                pending=pending.append("+");
                et_input.setText(pending);
                break;
            case R.id.btn_minus:
                flag=false;
                pending=pending.append("-");
                et_input.setText(pending);
                break;
            case R.id.btn_multiply:
                flag=false;
                // if (last >= '0' && last <= '9' ) {
                pending = pending.append("*");
                // }
                et_input.setText(pending);
                break;
            case R.id.btn_divide:
                flag=false;
                pending = pending.append("/");
                et_input.setText(pending);
                break;
            case R.id.btn_point:
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(judge1()){
                    if(pending.length()==0 || last=='+' || last=='-' || last=='*' || last=='/')
                    pending = pending.append("0");
                    pending = pending.append(".");
                    et_input.setText(pending);
                } break;
            case R.id.btn_left:    //左括号
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(last!='(' || (last<='0'&&last>='9')){
                    pending = pending.append("(");
                    et_input.setText(pending);
                } break;
            case R.id.btn_right:   //右括号
                if(flag==true) {
                    pending=pending.delete(0,pending.length());
                    flag=false;
                }
                if(last>='0'&&last<='9' || last==')' && judge2()==1){
                     pending=pending.append(")");
                     et_input.setText(pending);
                } break;
            case R.id.btn_del: //删除
                if(pending.length()!=0){
                  pending=pending.delete(pending.length()-1,pending.length());
                  et_input.setText(pending);
                    mTimeHandler.sendEmptyMessageDelayed(0, 800);  //刷新页面
                } break;
            case R.id.btn_clear:  //清空
                pending=pending.delete(0,pending.length());
                pending=pending.append("0");
                mTimeHandler.sendEmptyMessageDelayed(0, 800);  //刷新页面
                flag=true;//设置标识位
                break;
            case R.id.btn_equal:   //等于

                  if(pending.length()>1){
                      InfixInToDuffix inf=new InfixInToDuffix();
                      String result;
                      try{
                          String a=inf.toSuffix(pending);
                          result=inf.dealEquation(a);
                          if(result.equals(inf.toSuffix(pending))){
                              Toast.makeText(Digital_calculate.this, "结果是：" + result, Toast.LENGTH_SHORT).show();
                              break;
                          }

                          if(result.equals("运算失败"))
                              break;

                      }
                      catch(Exception ex) {
                          result="出错";
                          flag=true;
                          Toast.makeText(Digital_calculate.this, "请重新输入",Toast.LENGTH_SHORT).show();
                      }
                      pending=pending.delete(0,pending.length());  //清空文本框内容

                      if(result.indexOf("0.")!=0 && result.indexOf("0")==0){
                          result="0";
                      }


                      if(flag==false){
                          if(result.matches("-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?")){
                              /* 使用java正则表达式去掉多余的.与0 */
                              if(result.indexOf(".") > 0){
                                  result = result.replaceAll("0+?$", "");//去掉多余的0
                                  result = result.replaceAll("[.]$", "");//如最后一位是.则去掉
                              }

                              pending=pending.append(result);
                              Toast.makeText(Digital_calculate.this, "结果是：" + result, Toast.LENGTH_SHORT).show();
                          }
                          else{
                              et_input.setText(pending+"="+result);   //输出结果
                          }
                      }
                      else{
                          pending=pending.append("出错");
                      }
                          flag=true;//设置标识位
                          mTimeHandler.sendEmptyMessageDelayed(0, 800);  //刷新页面
                  }  break;
            default: break;
        }
    }

    private boolean judge1(){
            String a="+-*/.";
            int [] b=new int[a.length()];
            int max;
            for(int i=0;i<a.length();i++){
                String c=""+a.charAt(i);
                b[i]=pending.lastIndexOf(c);  //lastIndexOf 最后一次出现的位置
            }
            Arrays.sort(b);  //从大到小排序
            if(b[a.length()-1]==-1) {
                max=0;
            }
            else{
                max=b[a.length()-1];
            }
            if(pending.indexOf(".",max)==-1) {  //从max位置开始查找.在数组中的位置
                return true;   //找不到就输出-1
            }
            else{
                return false;
            }
        }   //小数点判断

    private int judge2(){      //右括号判断
         int a=0, b=0;
         for(int i=0;i<pending.length();i++){
             if(pending.charAt(i)=='('){
                 a++;
             }
             if(pending.charAt(i)==')'){
                 b++;
             }
         }
         if(a==b){
             return 0;   //左右括号相等
         }
         if(a>b){
             return 1;   //左括号大于右括号
         }
         return 2;       //右括号大于左括号
    }    //右括号判断

    private boolean judge3(){      //0输入判断

        InfixInToDuffix inf1=new InfixInToDuffix();
        if(inf1.toSuffix(pending).matches("-[0]+([0]+)?|[0]+([0]+)?") )
            return false;
        return true;
    }     //0输入判断

    private boolean judge4(){      //0输入判断
        int last1 = 0;
        if (pending.length() != 0) {
            last1 = pending.codePointAt(pending.length() - 1);
        }

        InfixInToDuffix inf1=new InfixInToDuffix();
      //  if((inf1.toSuffix(pending)).compareTo("[0]"))
        if(inf1.toSuffix(pending).matches("-[0]+([0]+)?|[0]+([0]+)?"))
            pending=pending.delete(pending.length()-1,pending.length());
            return true;
    }     //1-9输入判断

   //设置定时器

    //在类里声明一个Handler
    Handler mTimeHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                et_input.setText(pending);
                sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

    //清0计时器
    Handler mTimeHandler_clean = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                et_input.setText(pending_clean);
                sendEmptyMessageDelayed(0, 1000);
            }
        }
    };
}
