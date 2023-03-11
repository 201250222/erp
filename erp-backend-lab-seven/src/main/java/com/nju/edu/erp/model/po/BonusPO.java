package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BonusPO {
    /**
     * 员工编号
     */
    private Integer staffId;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 员工银行账户信息
     */
    private String staffBankCard;
    /**
     * 应发工资
     */
    private Integer originSalary;
    /**
     * 扣除税款
     */
    private Integer tax;
    /**
     * 缺席扣除
     */
    private Integer absenceDeduction;
    /**
     * 实际工资
     */
    private Integer actualSalary;
    /**
     * 年份
     */
    private String year;
    /**
     * 年终奖
     */
    private BigDecimal bonus;
}
