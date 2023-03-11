package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.CreditSheetState;
import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSheetPO {
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
}
