package com.nju.edu.erp.model.vo;

import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.SalaryEnum;
import com.nju.edu.erp.service.salaryStrategy.Salary;
import com.nju.edu.erp.service.salaryStrategy.strategies.MonthPerformanceSalary;
import com.nju.edu.erp.service.salaryStrategy.strategies.MonthSalary;
import com.nju.edu.erp.service.salaryStrategy.strategies.YearSalary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffInfoVO {

    /**
     * 员工id
     */
    private Integer id;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 员工性别
     */
    private String gender;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 职位
     */
    private Role role;
    /**
     * 银行账户
     */
    private String bankCard;
    /**
     * 基本工资
     */
    private Integer performanceSalary;
    /**
     * 岗位工资
     */
    private Integer roleSalary;
    /**
     * 工资策略采用的方式
     */
    private SalaryEnum salaryCalculate;
    /**
     * 工资策略
     */
    private Salary salary;
    /**
     * 判断薪资模式
     */
    public void setRole(Role role){
        this.role = role;
        switch (role) {
            case SALE_MANAGER:
            case SALE_STAFF:
                this.salary = new Salary(new MonthPerformanceSalary());
                break;
            case GM:
                this.salary = new Salary(new YearSalary());
                break;
            case HR:
            case FINANCIAL_STAFF:
            case INVENTORY_MANAGER:
                this.salary = new Salary(new MonthSalary());
                break;
            default:
                break;
        }
    }
}
