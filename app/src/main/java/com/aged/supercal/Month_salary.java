package com.aged.supercal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


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
    private Spinner spinner;
    Button month_salary_tax;

    private String strCity;
    private double baseNum_1 = 5080;//社保汇缴基数
    private double baseNum_2 = 2273;//公积金汇缴
    private String[] arrCity={"北京","上海","广州","深圳","武汉","其他"};

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

        spinner= (Spinner) findViewById(R.id.spinner);
        //创建ArrayAdapter对象
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrCity);
        spinner.setAdapter(adapter);
        //监听函数
        setEditListenner();

    }

    public void setEditListenner(){

        month_salary_tax.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new ProvOnItemSelectedListener());
        //编辑框坚挺
        old_person.addTextChangedListener(new MyTexWatcher((EditText) old_person));
        old_person.setOnFocusChangeListener(new MyEditListenner((EditText)old_person));
        old_company.addTextChangedListener(new MyTexWatcher((EditText) old_company));
        old_company.setOnFocusChangeListener(new MyEditListenner((EditText)old_company));
        medical_person.addTextChangedListener(new MyTexWatcher((EditText) medical_person));
        medical_person.setOnFocusChangeListener(new MyEditListenner((EditText)medical_person));
        medical_company.addTextChangedListener(new MyTexWatcher((EditText) medical_company));
        medical_company.setOnFocusChangeListener(new MyEditListenner((EditText)medical_company));
        unemployee_person.addTextChangedListener(new MyTexWatcher((EditText) unemployee_person));
        unemployee_person.setOnFocusChangeListener(new MyEditListenner((EditText)unemployee_person));
        unemployee_company.addTextChangedListener(new MyTexWatcher((EditText) unemployee_company));
        unemployee_company.setOnFocusChangeListener(new MyEditListenner((EditText)unemployee_company));
        injury_person.addTextChangedListener(new MyTexWatcher((EditText) injury_person));
        injury_person.setOnFocusChangeListener(new MyEditListenner((EditText)injury_person));
        injury_company.addTextChangedListener(new MyTexWatcher((EditText) injury_company));
        injury_company.setOnFocusChangeListener(new MyEditListenner((EditText)injury_company));
        birth_person.addTextChangedListener(new MyTexWatcher((EditText) birth_person));
        birth_person.setOnFocusChangeListener(new MyEditListenner((EditText)birth_person));
        birth_company.addTextChangedListener(new MyTexWatcher((EditText) birth_company));
        birth_company.setOnFocusChangeListener(new MyEditListenner((EditText)birth_company));
        fund_person.addTextChangedListener(new MyTexWatcher((EditText) fund_person));
        fund_person.setOnFocusChangeListener(new MyEditListenner((EditText)fund_person));
        fund_company.addTextChangedListener(new MyTexWatcher((EditText) fund_company));
        fund_company.setOnFocusChangeListener(new MyEditListenner((EditText)fund_company));
    }

    public void getCity(){
        switch (strCity){
            case "北京":
                baseNum_1 = 5080; baseNum_2 = 2273;break;
            case "上海":
                baseNum_1 = 4279;
                baseNum_2 = 2300;break;
            case "广州":
                baseNum_1 = 2100;
                baseNum_2 = 2100;break;
            case "深圳":
                baseNum_1 = 2200;
                baseNum_2 = 2130;break;
            case "武汉":
                baseNum_1 = 3399.6;
                baseNum_2 = 1750;break;
            default:
                baseNum_1 = 5080; baseNum_2 = 2273;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_salary);

        Intitview();   //调用初始化函数

    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onPause();
    }


    public void onClick(View v){
        //传值
        Bundle bundle = new Bundle();
        DecimalFormat df=new DecimalFormat( "###############0.00 ");

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

            double result_person=0,  result_company=0,result_person_fund=0,result_company_fund=0, result_1=0;   //保存工资利息 //保存个人缴纳总和，单位总和

            getCity();//获取城市

            if(taxbefore_point<= baseNum_1){
                //个人缴纳
                double old_tax_person= baseNum_1 *oldperson*0.01;  //养老
                double medical_tax_person= baseNum_1 *medicalperson*0.01;  //医疗
                double unemployee_tax_person= baseNum_1 *unemployeeperson*0.01;  //失业
                double injury_tax_person= baseNum_1 *injuryperson*0.01;  //工伤
                double birth_tax_person= baseNum_1 *birthperson*0.01;  //生育
                double fund_tax_person= baseNum_2 *fundperson*0.01;  //公积金

                bundle.putString("old_taxperson", df.format(old_tax_person));  //个人养老
                bundle.putString("medical_taxperson", df.format(medical_tax_person));  //个人医疗
                bundle.putString("unemployee_taxperson", df.format(unemployee_tax_person));  //个人失业
                bundle.putString("injury_taxperson", df.format(injury_tax_person));  //个人工伤
                bundle.putString("birth_taxperson", df.format(birth_tax_person));  //个人生育
                bundle.putString("fund_taxperson", df.format(fund_tax_person));  //个人公积金


                result_person_fund=fund_tax_person;//个人公积金
                result_person=old_tax_person+medical_tax_person+unemployee_tax_person+injury_tax_person+birth_tax_person+fund_tax_person; //个人社保

                //单位缴纳
                double old_tax_company= baseNum_1 *oldcompany*0.01;  //养老
                double medical_tax_conmpany= baseNum_1 *medicalcompany*0.01;  //医疗
                double unemployee_tax_company= baseNum_1 *unemployeecompany*0.01;  //失业
                double injury_tax_company= baseNum_1 *injurycompany*0.01;  //工伤
                double birth_tax_company= baseNum_1 *birthcompany*0.01;  //生育
                double fund_tax_company= baseNum_2 *fundcompany*0.01;  //公积金

                bundle.putString("old_taxcompany", df.format(old_tax_company));  //个人养老
                bundle.putString("medical_taxcompany", df.format(medical_tax_conmpany));  //个人医疗
                bundle.putString("employee_tax_company", df.format(unemployee_tax_company));  //个人失业
                bundle.putString("injury_taxcompany", df.format(injury_tax_company));  //个人工伤
                bundle.putString("birth_taxcompany", df.format(birth_tax_company));  //个人生育
                bundle.putString("fund_taxcompany", df.format(fund_tax_company));  //个人公积金

                Toast.makeText(Month_salary.this, df.format(medical_tax_conmpany),Toast.LENGTH_SHORT).show();

                result_company_fund=fund_tax_company; //公司公积金
                result_company=old_tax_company+medical_tax_conmpany+unemployee_tax_company+injury_tax_company+birth_tax_company+fund_tax_company;  //公司社保

            }


            if(taxbefore_point> baseNum_1){
                //个人缴纳
                double old_tax_person=taxbefore_point*oldperson*0.01;  //养老
                double medical_tax_person=taxbefore_point*medicalperson*0.01;  //医疗
                double unemployee_tax_person=taxbefore_point*unemployeeperson*0.01;  //失业
                double injury_tax_person=taxbefore_point*injuryperson*0.01;  //工伤
                double birth_tax_person=taxbefore_point*birthperson*0.01;  //生育
                double fund_tax_person=taxbefore_point*fundperson*0.01;  //公积金

                bundle.putString("old_taxperson", df.format(old_tax_person));  //个人养老
                bundle.putString("medical_taxperson", df.format(medical_tax_person));  //个人医疗
                bundle.putString("unemployee_taxperson", df.format(unemployee_tax_person));  //个人失业
                bundle.putString("injury_taxperson", df.format(injury_tax_person));  //个人工伤
                bundle.putString("birth_taxperson", df.format(birth_tax_person));  //个人生育
                bundle.putString("fund_taxperson", df.format(fund_tax_person));  //个人公积金

                result_person_fund=fund_tax_person;//个人公积金
                result_person=old_tax_person+medical_tax_person+unemployee_tax_person+injury_tax_person+birth_tax_person+fund_tax_person;


                //单位缴纳
                double old_tax_company=taxbefore_point*oldcompany*0.01;  //养老
                double medical_tax_company=taxbefore_point*medicalcompany*0.01;  //医疗
                double unemployee_tax_company=taxbefore_point*unemployeecompany*0.01;  //失业
                double injury_tax_company=taxbefore_point*injurycompany*0.01;  //工伤
                double birth_tax_company=taxbefore_point*birthcompany*0.01;  //生育
                double fund_tax_company=taxbefore_point*fundcompany*0.01;  //公积金

                bundle.putString("old_taxcompany", df.format(old_tax_company));  //个人养老
                bundle.putString("medical_taxcompany", df.format(medical_tax_company));  //个人医疗
                bundle.putString("employee_tax_company", df.format(unemployee_tax_company));  //个人失业
                bundle.putString("injury_taxcompany", df.format(injury_tax_company));  //个人工伤
                bundle.putString("birth_taxcompany", df.format(birth_tax_company));  //个人生育
                bundle.putString("fund_taxcompany", df.format(fund_tax_company));  //个人公积金

                result_company_fund=fund_tax_company; //公司公积金
                result_company=old_tax_company+medical_tax_company+unemployee_tax_company+injury_tax_company+birth_tax_company+fund_tax_company;
            }

            double tax_person=0;   //保存个人所得税结果
            if(taxbefore_point<=taxstart_point){
                tax_person=0;
            }
            if(taxbefore_point>taxstart_point){   //大于起征点
                tax_person=taxbefore_point-taxstart_point-result_person;
                //计算工资利息

                if(tax_person<=3000)  result_1=tax_person*0.03;   //不超过3000
                if(3000<tax_person && tax_person<=12000)   result_1=tax_person*0.10-210;    //3000-12000
                if(12000<tax_person && tax_person<=25000)   result_1=tax_person*0.20-1410;   //12000-25000
                if(25000<tax_person && tax_person<=35000)  result_1=tax_person*0.25-2660;   //25000-35000
                if(35000<tax_person && tax_person<=55000)  result_1=tax_person*0.30-4410;   //35000-55000
                if(55000<tax_person && tax_person<=80000)  result_1=tax_person*0.35-7160;   //55000-80000
                if(tax_person>80000)                           result_1=tax_person*0.45-15160;   //大于80000                           tax_person=tax_person*0.45-181920;   //大于9600000
            }


            //跳转结果界面
            Intent i=new Intent(Month_salary.this,Month_salary_result.class);


            bundle.putString("tax_daoshou_income", df.format(taxbefore_point-result_person-result_1));  //到手工资
            bundle.putString("tax_before_income", df.format(taxbefore_point));  //税前收入
            bundle.putString("person_socail_shebao", df.format(result_person-result_person_fund));  //个人社保
            bundle.putString("person_socail_funds", df.format(result_person_fund));  //个人公积金
            bundle.putString("person_total_tax", df.format(result_1));  //个人公积金
            bundle.putString("person_total_jiaona", df.format(result_person+result_1));  //个人缴纳
            bundle.putString("company_total_jiaona", df.format(result_company));  //个人缴纳

//            Toast.makeText(Month_salary.this, df.format(result_person_fund),Toast.LENGTH_SHORT).show();

            i.putExtras(bundle);


            startActivity(i);

        }
    }

    //Spinner OnItemSelected监听器
    private class  ProvOnItemSelectedListener implements OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> adapter,View view,int position,long id) {
            //获取选择的项的值
            strCity=adapter.getItemAtPosition(position).toString();
//            Toast.makeText(getApplicationContext(), strCity, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            String sInfo="什么也没选！";
            Toast.makeText(getApplicationContext(),sInfo, Toast.LENGTH_LONG).show();

        }
    }
    //小数点监听 2 个class
    class MyTexWatcher implements TextWatcher{
        boolean deleteLastChar;// 是否需要删除末尾
        private EditText editText;
        public MyTexWatcher(EditText editText){
            this.editText = editText;
        }

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
                editText.setText(s.toString().substring(0, s.toString().length() - 1));
                // 光标强制到末尾
                editText.setSelection(editText.getText().length());
            }
            // 以小数点开头，前面自动加上 "0"
            if (s.toString().startsWith(".")) {
                editText.setText("0" + s);
                editText.setSelection(editText.getText().length());
            }
        }
    }
    class MyEditListenner implements View.OnFocusChangeListener {
        public EditText editText;

        public MyEditListenner(EditText editText){
            this.editText = editText;
        }
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText editText1 = (EditText) v;
            // 以小数点结尾，去掉小数点
            if (!hasFocus && editText1.getText() != null && editText1.getText().toString().endsWith(".")) {
                editText.setText(editText1.getText().subSequence(0, editText1.getText().length() - 1));
                editText.setSelection(editText.getText().length());
            }
        }
    }

}
