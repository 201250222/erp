package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.CreditSheetState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.TypeHandler;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditSheetPO {
    /**
     * 收款单单据编号（格式为：SKD-yyyyMMdd-xxxxx)
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
}
