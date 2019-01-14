package com.aged.supercal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Month_salary extends AppCompatActivity implements View.OnClickListener{

    private TextView tax_before_point;
    private TextView tax_start_point;
    private TextView old_person;
    private TextView old_company;
    private TextView  medical_person;
    private TextView medical_company;
    private TextView unemployee_person;
    private TextView unemployee_company;
    private TextView injury_person;
    private TextView injury_company;
    private TextView birth_person;
    private TextView birth_company;
    private TextView fund_person;
    private TextView fund_company;
    Button month_salary_tax;

    public void Intitview(){
        month_salary_tax = (Button) findViewById(R.id.month_salary_tax);  //计算按钮

        tax_before_point=(TextView) findViewById(R.id.tax_before_point);
        tax_start_point=(TextView) findViewById(R.id.tax_start_point);
        old_person=(TextView) findViewById(R.id.old_person);
        old_company=(TextView) findViewById(R.id.old_company);
        medical_person=(TextView) findViewById(R.id.medical_person);
        medical_company=(TextView) findViewById(R.id.medical_company);
        unemployee_person=(TextView) findViewById(R.id.unemployee_person);
        unemployee_company=(TextView) findViewById(R.id.unemployee_company);
        injury_person=(TextView) findViewById(R.id.injury_person);
        injury_company=(TextView) findViewById(R.id.injury_company);
        birth_person=(TextView) findViewById(R.id.birth_person);
        birth_company=(TextView) findViewById(R.id.birth_company);
        fund_person=(TextView) findViewById(R.id.fund_person);
        fund_company=(TextView) findViewById(R.id.fund_company);

        month_salary_tax.setOnClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_salary);

        Intitview();   //调用初始化函数
    }

    public void onClick(View v){
        if(tax_start_point.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(tax_before_point.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(old_person.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(old_company.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(medical_person.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(medical_company.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(unemployee_person.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(unemployee_company.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(injury_person.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(injury_company.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(birth_person.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(birth_company.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(fund_person.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else if(fund_company.getText().toString().equals("")){
            Toast.makeText(Month_salary.this, "输入不能为空",Toast.LENGTH_SHORT).show();
        }else {

            double taxbefore_point=Double.parseDouble(tax_before_point.getText().toString());
            double taxstart_point=Double.parseDouble(tax_start_point.getText().toString());
            double oldperson=Double.parseDouble(old_person.getText().toString());
            double oldcompany=Double.parseDouble(old_company.getText().toString());
            double medicalperson=Double.parseDouble(medical_person.getText().toString());
            double medicalcompany=Double.parseDouble(medical_company.getText().toString());
            double unemployeeperson=Double.parseDouble(unemployee_person.getText().toString());
            double unemployeecompany=Double.parseDouble(unemployee_company.getText().toString());
            double injuryperson=Double.parseDouble(injury_person.getText().toString());
            double injurycompany=Double.parseDouble(injury_company.getText().toString());
            double birthperson=Double.parseDouble(birth_person.getText().toString());
            double birthcompany=Double.parseDouble(birth_company.getText().toString());
            double fundperson=Double.parseDouble(fund_person.getText().toString());
            double fundcompany=Double.parseDouble(fund_company.getText().toString());

            double result_person=0,  result_company=0; //保存个人缴纳总和，单位总和

            if(taxbefore_point<=2400){
                //个人缴纳
                double old_tax_person=2400*oldperson;  //养老
                double medical_tax_person=2400*medicalperson;  //医疗
                double unemployee_tax_person=2400*unemployeeperson;  //失业
                double injury_tax_person=2400*injuryperson;  //工伤
                double birth_tax_person=2400*birthperson;  //生育
                double fund_tax_person=2400*fundperson;  //公积金

                result_person=old_tax_person+medical_tax_person+unemployee_tax_person+injury_tax_person+birth_tax_person+fund_tax_person;

                //单位缴纳
                double old_tax_company=2400*oldcompany;  //养老
                double medical_tax_conmpany=2400*medicalcompany;  //医疗
                double unemployee_tax_company=2400*unemployeecompany;  //失业
                double injury_tax_company=2400*injurycompany;  //工伤
                double birth_tax_company=2400*birthcompany;  //生育
                double fund_tax_company=2400*fundcompany;  //公积金

                result_company=old_tax_company+medical_tax_conmpany+unemployee_tax_company+injury_tax_company+birth_tax_company+fund_tax_company;

            }


            if(taxbefore_point>2400){
                //个人缴纳
                double old_tax_person=taxbefore_point*oldperson;  //养老
                double medical_tax_person=taxbefore_point*medicalperson;  //医疗
                double unemployee_tax_person=taxbefore_point*unemployeeperson;  //失业
                double injury_tax_person=taxbefore_point*injuryperson;  //工伤
                double birth_tax_person=taxbefore_point*birthperson;  //生育
                double fund_tax_person=taxbefore_point*fundperson;  //公积金

                result_person=old_tax_person+medical_tax_person+unemployee_tax_person+injury_tax_person+birth_tax_person+fund_tax_person;


                //单位缴纳
                double old_tax_company=taxbefore_point*oldcompany;  //养老
                double medical_tax_conmpany=taxbefore_point*medicalcompany;  //医疗
                double unemployee_tax_company=taxbefore_point*unemployeecompany;  //失业
                double injury_tax_company=taxbefore_point*injurycompany;  //工伤
                double birth_tax_company=taxbefore_point*birthcompany;  //生育
                double fund_tax_company=taxbefore_point*fundcompany;  //公积金

                result_company=old_tax_company+medical_tax_conmpany+unemployee_tax_company+injury_tax_company+birth_tax_company+fund_tax_company;
            }

            double tax_person=0;   //保存个人所得税结果
            if(taxbefore_point<=taxstart_point){
                tax_person=0;
            }
            if(taxbefore_point>taxstart_point){   //大于起征点
                tax_person=taxbefore_point-taxstart_point-result_person;
              //  if()

            }


               //跳转结果界面
               Intent i=new Intent(Month_salary.this,Month_salary_result.class);

               startActivity(i);

        }
    }
}
