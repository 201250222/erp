package com.nju.edu.erp.service.salaryStrategy;

import lombok.Data;

import java.io.Serializable;

@Data
public class Salary implements Serializable {

    private SalaryCalculate salaryCalculator;

    public Salary(SalaryCalculate salaryCalculator) {
        this.salaryCalculator = salaryCalculator;
    }

    public Integer calculate(Integer roleSalary, Integer performance){
        return this.salaryCalculator.calculate(roleSalary,performance);
    }

    public boolean release(String date){
        return this.salaryCalculator.release(date);
    }
}
