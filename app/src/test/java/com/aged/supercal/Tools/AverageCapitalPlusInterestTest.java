package com.aged.supercal.Tools;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class AverageCapitalPlusInterestTest {
    private int invest = 1*10000;
    private int totalMonth = 1* 12;
    private double yearRate = 4.35/100;
    private AverageCapitalPlusInterest averageCapitalPlusInterest;
    private Map<Integer,BigDecimal> map_PerMonthLeftPrin;
    private Map<Integer,BigDecimal> map_PerMonthRest;
    private  Map<Integer,BigDecimal>  map_getPerMonthPrincipal;
    private BigDecimal totalRestxi;
    private BigDecimal totalMoneyxi;
    private BigDecimal perMonPrin;

    @Before
    public void setUp() throws Exception {
        averageCapitalPlusInterest = new AverageCapitalPlusInterest();
        totalRestxi = averageCapitalPlusInterest.getInterestCount(invest,yearRate,totalMonth).setScale(2, BigDecimal.ROUND_DOWN);
        totalMoneyxi = averageCapitalPlusInterest.getPrincipalInterestCount(invest,yearRate,totalMonth).setScale(2, BigDecimal.ROUND_DOWN);
        //每月剩余贷款金额
        map_PerMonthLeftPrin = averageCapitalPlusInterest.getLeftInvest(invest,yearRate,totalMonth);
        //每月利息
        map_PerMonthRest = averageCapitalPlusInterest.getPerMonthInterest(invest,yearRate,totalMonth);
        //每月归还本金
        map_getPerMonthPrincipal = averageCapitalPlusInterest.getPerMonthPrincipal(invest,yearRate,totalMonth);
        //每月归还的本金+利息（月供）
        perMonPrin = averageCapitalPlusInterest.getPerMonthPrincipalInterest(invest,yearRate,totalMonth).setScale(2, BigDecimal.ROUND_DOWN);


    }

    @Test
    public void getLeftInvest() {
        BigDecimal temp = new BigDecimal(9183.16).setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(temp,map_PerMonthLeftPrin.get(1));
    }

    @Test
    public void getPerMonthPrincipalInterest() {
        BigDecimal temp = new BigDecimal(853.09).setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(temp,perMonPrin);

    }

    @Test
    public void getPerMonthInterest() {
        BigDecimal temp = new BigDecimal(36.24).setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(temp,map_PerMonthRest.get(1));
    }

    @Test
    public void getPerMonthPrincipal() {
        BigDecimal temp = new BigDecimal(816.85).setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(temp,map_getPerMonthPrincipal.get(1));
    }

    @Test
    public void getInterestCount() {
        BigDecimal temp = new BigDecimal(237.14).setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(temp,totalRestxi);
    }

//    @Test
//    public void getPrincipalInterestCount() {
//        BigDecimal temp = new BigDecimal(10237.09).setScale(2,BigDecimal.ROUND_DOWN);
//        assertEquals(temp,totalMoneyxi);
//    }
}