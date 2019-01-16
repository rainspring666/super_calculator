package com.aged.supercal.Tools;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class AverageCapitalTest {

    private int invest = 1*10000;
    private int totalMonth = 1* 12;
    private double yearRate = 4.35/100;
    private AverageCapital averageCapital;

    //每月剩余贷款金额
    private Map<Integer,BigDecimal> map_PerMonthLeftPrin ;
    //每月利息
    private Map<Integer,BigDecimal> map_PerMonthRest ;
    //每月归还的本金+利息（月供）
    private Map<Integer,BigDecimal>  map_PerMonthPrincipalInterest ;


    @Before
    public void setUp() throws Exception {
        averageCapital = new AverageCapital();
    }

    @Test
    public void getPerMonthPrincipal() {
        BigDecimal temp = new BigDecimal(833.33).setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(temp,averageCapital.getPerMonthPrincipal(invest,totalMonth));
    }

    @Test
    public void getLeftInvest() {

    }

    @Test
    public void getPerMonthRest() {
        map_PerMonthRest = averageCapital.getPerMonthRest(invest,yearRate,totalMonth);
        BigDecimal temp = new BigDecimal(36.24).setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(temp,map_PerMonthRest.get(1));
    }

    @Test
    public void getPerMonthPrincipalInterest() {
        map_PerMonthPrincipalInterest = averageCapital.getPerMonthPrincipalInterest(invest,yearRate,totalMonth);
        BigDecimal temp = new BigDecimal(869.57).setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(temp,map_PerMonthPrincipalInterest.get(1));
    }

    @Test
    public void getTotalRest() {
        BigDecimal temp = new BigDecimal(235.56).setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(temp,averageCapital.getTotalRest(invest,yearRate,totalMonth));
    }

    @Test
    public void getTotalMoney() {
        BigDecimal temp = new BigDecimal(10235.52).setScale(2,BigDecimal.ROUND_DOWN);
        assertEquals(temp,averageCapital.getTotalMoney(invest,yearRate,totalMonth));
    }
}