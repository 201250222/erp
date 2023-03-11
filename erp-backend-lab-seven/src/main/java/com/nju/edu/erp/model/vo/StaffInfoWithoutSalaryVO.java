package com.nju.edu.erp.model.vo;

import com.nju.edu.erp.enums.Role;
import com.nju.edu.erp.enums.SalaryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffInfoWithoutSalaryVO {

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
}
