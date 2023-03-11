package com.nju.edu.erp.model.vo;

import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.enums.sheetState.SalarySheetState;
import com.nju.edu.erp.enums.sheetState.WarehousePresentSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalarySheetVO implements SheetVO, ManageProcSheetVO{
    /**
     * 工资单单据编号（格式为：GZD-yyyyMMdd-xxxxx), 新建单据时前端传null
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
     * 单据状态
     */
    private SalarySheetState state;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 单据类型
     */
    private ManageProcType type;

    @Override
    public boolean betweenDate(Date beginDate, Date endDate) {
        if(beginDate == null) return true;
        return createTime.after(beginDate) && createTime.before(endDate);
    }

    @Override
    public boolean isSalesMan(String salesMan) {
        if(salesMan == null) return true;
        return Objects.equals(operator, salesMan);
    }
    @Override
    public boolean isAllSuccessful(boolean allSuccessful) {
        if(allSuccessful){
            return state== SalarySheetState.SUCCESS;
        }
        return true;
    }
}
