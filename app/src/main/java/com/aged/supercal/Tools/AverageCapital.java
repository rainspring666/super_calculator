package com.aged.supercal.Tools;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AverageCapital {

     //等额本金计算---每月归还本金
    public BigDecimal getPerMonthPrincipal(double invest, int totalMonth) {
        BigDecimal monthIncome = new BigDecimal(invest).divide(new BigDecimal(totalMonth), 2, BigDecimal.ROUND_DOWN);
        return monthIncome;
    }

    //等额本金计算---每月剩余贷款金额
    public Map<Integer,BigDecimal> getLeftInvest(double invest, int totalMonth){
        BigDecimal big_invest = new BigDecimal(invest);
        Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();
        //每月本金
        BigDecimal monthPri = getPerMonthPrincipal(invest, totalMonth);
        for(int i=1;i<=totalMonth;i++){
            BigDecimal perLeftPrin = big_invest.subtract(monthPri.multiply(new BigDecimal(i)));
            perLeftPrin =  perLeftPrin.setScale(2, BigDecimal.ROUND_DOWN);
            map.put(i,perLeftPrin);
        }
        return map;
    }

    //等额本金计算---每月利息
    public Map<Integer,BigDecimal> getPerMonthRest(double invest,double yearRate,int totalMonth){
        Map<Integer,BigDecimal> map = new HashMap<Integer, BigDecimal>();
        BigDecimal big_invest = new BigDecimal(invest);
        // 每月本金
        BigDecimal monthPri = getPerMonthPrincipal(invest, totalMonth);
        // 获取月利率
        double monthRate = yearRate / 12;
        monthRate = new BigDecimal(monthRate).setScale(6, BigDecimal.ROUND_DOWN).doubleValue();
        for (int i = 1; i <= totalMonth; i++) {
//            double monthRes = (invest - monthPri * (i - 1)) * monthRate;
            BigDecimal big_mothRes =
                    new BigDecimal(monthRate).multiply(big_invest.subtract(monthPri.multiply(new BigDecimal(i - 1))));
            big_mothRes = big_mothRes.setScale(2, BigDecimal.ROUND_DOWN);
            map.put(i, big_mothRes);
        }
        return map;
    }

    //等额本金计算----每月归还本金monthPri+利息（月供）
    public Map<Integer, BigDecimal> getPerMonthPrincipalInterest(double invest, double yearRate, int totalMonth) {
        Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();
        BigDecimal big_invest = new BigDecimal(invest);
        // 每月本金
        BigDecimal monthPri = getPerMonthPrincipal(invest, totalMonth);
        // 获取月利率
        double monthRate = yearRate / 12;
        monthRate = new BigDecimal(monthRate).setScale(6, BigDecimal.ROUND_DOWN).doubleValue();
        for (int i = 1; i <= totalMonth; i++) {
//            double monthRes = monthPri + (invest - monthPri * (i - 1)) * monthRate;
            BigDecimal big_monthRes = monthPri.add(new BigDecimal(monthRate).multiply(big_invest.subtract(monthPri.multiply(new BigDecimal(i - 1)))));
            big_monthRes = big_monthRes.setScale(2, BigDecimal.ROUND_DOWN);
            map.put(i, big_monthRes);
        }
        return map;
    }

    //等额本金计算---累计利息
    public BigDecimal getTotalRest(double invest, double yearRate, int totalMonth){
        BigDecimal count = new BigDecimal(0);
        Map<Integer, BigDecimal> mapInterest = getPerMonthRest(invest, yearRate, totalMonth);

        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            count = count.add(entry.getValue());
        }
        return count;
    }
    //等额本金计算---累计还款金额
    public BigDecimal getTotalMoney(double invest,double yearRate,int totalMonth){
        BigDecimal count = new BigDecimal(0);
        Map<Integer, BigDecimal> mapInterest = getPerMonthPrincipalInterest(invest, yearRate, totalMonth);

        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            count = count.add(entry.getValue());
        }
        return count;
    }

}
