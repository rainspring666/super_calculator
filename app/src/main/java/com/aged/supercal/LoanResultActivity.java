package com.aged.supercal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanResultActivity extends AppCompatActivity {

    private TextView textView_invest;
    private TextView textView_yearRate;
    private TextView textView_year;
    private TextView textView_totalRest;
    private TextView textView_totalMoney;
    private TextView textView_firstMonth;
    private ListView listview;
    private int year_int=1;
    private Map<Integer, BigDecimal> map_PerMonthLeftPrin ;
    private Map<Integer, BigDecimal> map_PerMonthRest ;
    private Map<Integer, BigDecimal> map_PerMonthPrincipalInterest;

    private Map<Integer, BigDecimal> map_PerMonthLeftPrin_xi ;
    private Map<Integer, BigDecimal> map_PerMonthRest_xi ;
    private Map<Integer, BigDecimal> map_PerMonthPrincipalInterest_xi;
    private double perMonPrin=0;//等额本金 月供本金
    private int fangshi=1;

    public void initView(){

        textView_invest = (TextView)findViewById(R.id.textView20);
        textView_yearRate = (TextView)findViewById(R.id.textView21);
        textView_year = (TextView)findViewById(R.id.textView23);
        textView_totalRest = (TextView)findViewById(R.id.textView25);
        textView_totalMoney = (TextView)findViewById(R.id.textView27);
        textView_firstMonth = (TextView)findViewById(R.id.textView18);
        listview = (ListView)findViewById(R.id.listView);

        processData();

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,putData(),R.layout.activity_loan_res_item,
//                new String[]{"num","yuegong","benjin","lixi","shengyu"},
                new String[]{"num1","num2","num3","num4","num5"},
                new int[]{R.id.textView35,R.id.textView37,R.id.textView38,R.id.textView36,R.id.textView34});
        listview.setAdapter(simpleAdapter);

    }
    public List<Map<String,Object>> putData(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i=1;i<=year_int*12;i++){
            Map<String,Object> map = new HashMap<String,Object>();

            if(fangshi == 2){//等额本金jin
                BigDecimal yuegongb = map_PerMonthPrincipalInterest.get(i);
                BigDecimal lixi = map_PerMonthRest.get(i);
                BigDecimal shengyu = map_PerMonthLeftPrin.get(i);
                map.put("num1","第"+i+"期");
                map.put("num2",yuegongb+"");
                map.put("num3",perMonPrin+"");
                map.put("num4",lixi+"");
                map.put("num5",shengyu+"");
            }else if(fangshi == 1){//本息xi

                map_PerMonthLeftPrin_xi = (HashMap<Integer, BigDecimal>)getIntent().getSerializableExtra("map1");
                map_PerMonthRest_xi = (HashMap<Integer, BigDecimal>)getIntent().getSerializableExtra("map2");
                map_PerMonthPrincipalInterest_xi = (HashMap<Integer, BigDecimal>)getIntent().getSerializableExtra("map3");

                BigDecimal yuegongbxi = map_PerMonthPrincipalInterest_xi.get(i);
                BigDecimal lixixi = map_PerMonthRest_xi.get(i);
                BigDecimal shengyuxi = map_PerMonthLeftPrin_xi.get(i);
                map.put("num1","第"+i+"期");
                map.put("num2",perMonPrin+"");
                map.put("num3",yuegongbxi+"");
                map.put("num4",lixixi+"");
                map.put("num5",shengyuxi+"");
            }

            list.add(map);

        }
        return list;
    }

    public void processData(){
        //通过Activity.getIntent()获取当前页面接收到的Intent。
        Intent intent =getIntent();
        //getXxxExtra方法获取Intent传递过来的数据
        String totalRest = intent.getStringExtra("totalRest");
        String totalMoney = intent.getStringExtra("totalMoney");
        String yearRate = intent.getStringExtra("yearRate");
        String invest = intent.getStringExtra("invest");
        String year = intent.getStringExtra("year");
        String perMonPrin_str = intent.getStringExtra("perMonPrin");
        perMonPrin = Double.parseDouble(perMonPrin_str);

        fangshi = intent.getIntExtra("fangshi",1);

        year_int = Integer.parseInt(year);
        textView_year.setText(year+"年");
        textView_totalMoney.setText(totalMoney);
        textView_yearRate.setText(yearRate);
        textView_invest.setText(invest);
        textView_totalRest.setText(totalRest);
        //剩余贷款金额;每月利息；月供
        map_PerMonthLeftPrin = (HashMap<Integer, BigDecimal>)getIntent().getSerializableExtra("map1");
        map_PerMonthRest = (HashMap<Integer, BigDecimal>)getIntent().getSerializableExtra("map2");
        map_PerMonthPrincipalInterest = (HashMap<Integer, BigDecimal>)getIntent().getSerializableExtra("map3");
        if(fangshi == 2){
            String firstMonth = map_PerMonthPrincipalInterest.get(1)+"元";
            textView_firstMonth.setText(firstMonth);
        }else if(fangshi == 1){
            textView_firstMonth.setText(perMonPrin_str+"元");
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_result);

        initView();
//        processData();

    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.onPause();
    }

}
