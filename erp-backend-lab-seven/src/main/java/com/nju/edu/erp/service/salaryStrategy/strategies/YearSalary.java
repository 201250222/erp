package com.nju.edu.erp.service.salaryStrategy.strategies;

import com.nju.edu.erp.service.salaryStrategy.SalaryCalculate;

import java.io.Serializable;


public class YearSalary implements SalaryCalculate , Serializable {
    @Override
    public Integer calculate(Integer roleSalary, Integer performanceSalary) {
        return roleSalary*12;
    }

    @Override
    public boolean release(String date) {
        return date.equals("01-01");
    }
}
