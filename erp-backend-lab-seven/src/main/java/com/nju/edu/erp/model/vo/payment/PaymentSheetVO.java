package com.nju.edu.erp.model.vo.payment;

import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.enums.sheetState.CreditSheetState;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.vo.ManageProcSheetVO;
import com.nju.edu.erp.model.vo.SheetVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSheetVO implements SheetVO, ManageProcSheetVO {
    /**
     * 收款单单据编号（格式为：XJFYD-yyyyMMdd-xxxxx)
     * 后端托管创建
     */
    private String id;

    /**
     * 账户名
     */
    private String accountName;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 付款总金额
     */
    private BigDecimal totalAmount;

    /**
     * 付款单状态
     */
    private PaymentSheetState paymentSheetState;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 条目清单
     */
    private List<PaymentSheetContentVO> paymentSheetContent;

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
}
