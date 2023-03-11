package com.nju.edu.erp.model.vo.table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailParamsVO {
    /**
     * 起始时间
     */
    private Date beginTime;
    /**
     * 终止时间
     */
    private Date endTime;
    /**
     * 商品名
     */
    private String productName;
    /**
     * 客户
     */
    private Integer customer;
    /**
     * 业务员
     */
    private String operator;
    /**
     * 只有一个仓库，所以仓库号会被忽略
     */
    private Integer warehouse;
}
