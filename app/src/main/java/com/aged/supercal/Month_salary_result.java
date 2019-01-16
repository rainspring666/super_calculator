package com.aged.supercal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Month_salary_result extends AppCompatActivity {

    private TextView month_real_money;
    private TextView month_before_money;
    private TextView month_real_shebao;
    private TextView month_real_fund;
    private TextView month_real_tax;
    private TextView month_perreal_jiaona;
    private TextView month_comreal_money;

    private TextView per_old;
    private TextView com_old;
    private TextView per_med;
    private TextView com_med;
    private TextView per_unemploy;
    private TextView com_unemplo;
    private TextView per_injur;
    private TextView com_inj;
    private TextView per_birth;
    private TextView com_birth;
    private TextView per_gongji;
    private TextView com_gongji;



    public void InitView(){
        month_real_money = (TextView) findViewById(R.id.month_real_money);
        month_before_money= (TextView) findViewById(R.id.month_before_money);
        month_real_shebao= (TextView) findViewById(R.id.month_real_shebao);
        month_real_fund = (TextView) findViewById(R.id.month_real_fund);
        month_real_tax= (TextView) findViewById(R.id.month_real_tax);
        month_perreal_jiaona= (TextView) findViewById(R.id.month_perreal_jiaona);
        month_comreal_money = (TextView) findViewById(R.id.month_comreal_money);

        per_old= (TextView) findViewById(R.id.per_old);
        com_old= (TextView) findViewById(R.id.com_old);
        per_med = (TextView) findViewById(R.id.per_med);
        com_med= (TextView) findViewById(R.id.com_med);
        per_unemploy= (TextView) findViewById(R.id.per_unemploy);
        com_unemplo = (TextView) findViewById(R.id.com_unemplo);
        per_injur= (TextView) findViewById(R.id.per_injur);
        com_inj= (TextView) findViewById(R.id.com_inj);
        per_birth = (TextView) findViewById(R.id.per_birth);
        com_birth= (TextView) findViewById(R.id.com_birth);
        per_gongji= (TextView) findViewById(R.id.per_gongji);
        com_gongji = (TextView) findViewById(R.id.com_gongji);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_salary_result);

        InitView();

        //接受值
        Bundle bunde = this.getIntent().getExtras();
        String tax_daoshou_income=bunde.getString("tax_daoshou_income");
        String tax_before_income=bunde.getString("tax_before_income");
        String person_socail_shebao=bunde.getString("person_socail_shebao");
        String person_socail_funds=bunde.getString("person_socail_funds");
        String person_total_tax=bunde.getString("person_total_tax");
        String person_total_jiaona=bunde.getString("person_total_jiaona");
        String company_total_jiaona=bunde.getString("company_total_jiaona");


        String old_taxperson=bunde.getString("old_taxperson");
        String old_taxcompany=bunde.getString("old_taxcompany");

        String medical_taxperson=bunde.getString("medical_taxperson");
        String medical_taxcompany=bunde.getString("medical_taxcompany");

        String unemployee_taxperson=bunde.getString("unemployee_taxperson");
        String employee_tax_company=bunde.getString("employee_tax_company");

        String injury_taxperson=bunde.getString("injury_taxperson");
        String injury_taxcompany=bunde.getString("injury_taxcompany");

        String birth_taxperson=bunde.getString("birth_taxperson");
        String birth_taxcompany=bunde.getString("birth_taxcompany");


        String fund_taxperson=bunde.getString("fund_taxperson");
        String fund_taxcompany=bunde.getString("fund_taxcompany");



        month_real_money.setText(tax_daoshou_income);
        month_before_money.setText(tax_before_income);
        month_real_shebao.setText(person_socail_shebao);
        month_real_fund.setText(person_socail_funds);
        month_real_tax.setText(person_total_tax);
        month_perreal_jiaona.setText(person_total_jiaona);
        month_comreal_money.setText(company_total_jiaona);

        per_old.setText(old_taxperson);
        com_old.setText(old_taxcompany);

        per_med.setText(medical_taxperson);
        com_med.setText(medical_taxcompany);

        per_unemploy.setText(unemployee_taxperson);
        com_unemplo.setText(employee_tax_company);

        per_injur.setText(injury_taxperson);
        com_inj.setText(injury_taxcompany);

        per_birth.setText(birth_taxperson);
        com_birth.setText(birth_taxcompany);

        per_gongji.setText(fund_taxperson);
        com_gongji.setText(fund_taxcompany);

    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onPause();
    }
}

