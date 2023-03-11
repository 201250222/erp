package com.nju.edu.erp.service.salaryStrategy.strategies;

import com.nju.edu.erp.service.salaryStrategy.SalaryCalculate;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthPerformanceSalary implements SalaryCalculate , Serializable {
    @Override
    public Integer calculate(Integer roleSalary, Integer performanceSalary) {
        if(performanceSalary==null||performanceSalary==0) return roleSalary;
        System.out.println("roleSalary:" + roleSalary + "  performance:" + performanceSalary);
        return roleSalary + performanceSalary;
    }

    @Override
    public boolean release(String date) {
        return date.endsWith("01");
    }
}
