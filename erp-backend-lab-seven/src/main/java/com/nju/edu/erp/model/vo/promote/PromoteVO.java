package com.nju.edu.erp.model.vo.promote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromoteVO {

    // 未填写的前端统一传null

    /**
     * 促销策略id (后端生成)
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
     * 触发条件类型(前端：[0：组合促销；1：总价促销])
     */
    private Integer promoteTrigger;
    /**
     * 客户等级(1~5级)
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
     * 促销类型(前端：[0: 折扣; 1: 赠品; 2: 代金券])
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
     * 折扣(0~1 之间，销售员最多 0.1，销售经理最多 0.2，总经理最多 1.0)
     */
    private Double discount;
    /**
     * 代金券金额
     */
    private BigDecimal voucherPrice;

}
