package com.nju.edu.erp.model.po;

import com.nju.edu.erp.enums.sheetState.WarehousePresentSheetState;
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
public class WarehousePresentSheetPO {
    /**
     * 库存赠送单id
     */
    private String id;
    /**
     * 赠品id
     */
    private String presentId;
    /**
     * 赠品数量
     */
    private Integer presentNum;
    /**
     * 关联销售单id
     */
    private String saleSheetId;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 创立时间
     */
    private Date createTime;
    /**
     * 赠送的总成本
     */
    private BigDecimal value;
    /**
     * 审核状态
     */
    private WarehousePresentSheetState state;
}

