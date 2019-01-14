package com.aged.supercal.Tools;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AverageCapitalTest {

    private int invest = 12;
    private int totalMonth = 12;
    private double yearRate = 4.35;
    private AverageCapital averageCapital;

    @Before
    public void setUp() throws Exception {
        averageCapital = new AverageCapital();
    }

    @Test
    public void getPerMonthPrincipal() {
        BigDecimal temp = new BigDecimal(1).setScale(2);
        assertEquals(temp,averageCapital.getPerMonthPrincipal(invest,totalMonth));
    }

    @Test
    public void getLeftInvest() {
    }

    @Test
    public void getPerMonthRest() {
    }

    @Test
    public void getPerMonthPrincipalInterest() {
    }

    @Test
    public void getTotalRest() {
    }

    @Test
    public void getTotalMoney() {
    }
}