package com.nju.edu.erp.model.vo.credit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditSheetContentVO {
    /**
     * 自增id, 新建单据时前端传null
     */
    private Integer id;

    /**
     * 收款单id, 新建单据时前端传null
     */
    private String purchaseSheetId;

    /**
     * 银行账户名称
     */
    String accountName;

    /**
     * 转账金额
     */
    BigDecimal amount;

    /**
     * 备注
     */
    String remark;
}
