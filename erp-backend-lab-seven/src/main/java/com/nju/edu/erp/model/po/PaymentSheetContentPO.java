package com.nju.edu.erp.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSheetContentPO {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 付款单id
     */
    private String paymentSheetId;


    /**
     * 客户
     */
    private Integer customer;


    /**
     * 转账金额
     */
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;
}
