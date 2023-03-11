package com.nju.edu.erp.model.vo.credit;

import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.enums.sheetState.CreditSheetState;
import com.nju.edu.erp.model.vo.ManageProcSheetVO;
import com.nju.edu.erp.model.vo.SheetVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditSheetVO implements SheetVO, ManageProcSheetVO {
    /**
     * 收款单单据编号（格式为：SKD-yyyyMMdd-xxxxx),新建时前端传null
     */
    private String id;

    /**
     * 客户id
     */
    private Integer customer;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 收款总金额
     */
    private BigDecimal totalAmount;

    /**
     * 收款单状态
     */
    private CreditSheetState creditSheetState;

    /**
     * 制定日期
     */
    private Date createTime;

    /**
     * 具体收款项目
     */
    private List<CreditSheetContentVO> creditSheetContent;

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
    public boolean isCustomer(Integer customer) {
        if(customer == null) return true;
        return Objects.equals(this.customer, customer);
    }

    @Override
    public boolean isSalesMan(String salesMan) {
        if(salesMan == null) return true;
        return Objects.equals(operator, salesMan);
    }
}
