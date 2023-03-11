package com.nju.edu.erp.model.vo.table;

import com.nju.edu.erp.model.vo.TableVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceTableVO implements TableVO {
    /**
     * 查询的起点时间（格式：yyyy-mm-dd）
     */
    private String beginDate;
    /**
     * 查询的终点时间（格式：yyyy-mm-dd）
     */
    private String endDate;
    /**
     * 折让后的收入（销售收入、商品类收入（商品报溢收入，成本调价收入，进货退货差价，代金券与实际收款差额收入））
     */
    private BigDecimal actualIncoming;
    /**
     * 折让的金额（销售折让金额，代金券总额）
     */
    private BigDecimal discounts;
    /**
     * 支出（销售成本、商品类支出（商品报损 商品赠出）、人力成本）
     */
    private BigDecimal outgoings;
    /**
     * 利润（折让后的收入-支出）
     */
    private BigDecimal profits;

}
