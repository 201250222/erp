package com.nju.edu.erp.service.salaryStrategy;

import java.util.Date;

public interface SalaryCalculate {

    Integer calculate(Integer roleSalary, Integer performanceSalary);

    boolean release(String date);
}
