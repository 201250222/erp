package com.nju.edu.erp.model.po;

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
public class PromotePO {

    /**
     * 促销策略id
     */
    private Integer id;
    /**
     * 创建人
     */
    private String operator;
    /**
     * 开始时间
     */
    private Date beginTime;
    /**
     * 过期时间
     */
    private Date endTime;
    /**
     * 触发条件类型
     */
    private Integer promoteTrigger;
    /**
     * 客户等级
     */
    private Integer customerLevel;
    /**
     * 第一个目标商品id
     */
    private String productId1;
    /**
     * 第一个目标商品触发数量
     */
    private Integer amount1;
    /**
     * 第二个目标商品id
     */
    private String productId2;
    /**
     * 第二个目标商品触发数量
     */
    private Integer amount2;
    /**
     * 第三个目标商品id
     */
    private String productId3;
    /**
     * 第三个目标商品触发数量
     */
    private Integer amount3;
    /**
     * 触发的总价
     */
    private BigDecimal totalPrice;
    /**
     * 促销类型
     */
    private Integer promoteType;
    /**
     * 赠品id
     */
    private String presentId;
    /**
     * 赠品数目
     */
    private Integer presentNumber;
    /**
     * 折扣
     */
    private Double discount;
    /**
     * 代金券金额
     */
    private BigDecimal voucherPrice;
}
