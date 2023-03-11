package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalarySheetPO {
    /**
     * 工资单单据编号（格式为：GZD-yyyyMMdd-xxxxx)
     */
    private String id;
    /**
     * 员工编号
     */
    private Integer staffID;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 应发日期(格式：yyyy-mm-dd)
     */
    private String releaseDate;
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
     * 操作员
     */
    private String operator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 单据状态
     */
    private SalarySheetState state;
}
