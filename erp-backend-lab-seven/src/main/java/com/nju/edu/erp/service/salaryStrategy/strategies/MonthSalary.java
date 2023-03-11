package com.nju.edu.erp.service.salaryStrategy.strategies;

import com.nju.edu.erp.service.salaryStrategy.SalaryCalculate;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthSalary implements SalaryCalculate , Serializable {
    @Override
    public Integer calculate(Integer roleSalary, Integer performanceSalary) {
        return roleSalary;
    }

    @Override
    public boolean release(String date) {
        return date.endsWith("01");
    }
}
