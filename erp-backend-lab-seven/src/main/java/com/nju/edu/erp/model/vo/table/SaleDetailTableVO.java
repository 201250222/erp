package com.nju.edu.erp.model.vo.table;

import com.nju.edu.erp.model.vo.TableVO;
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
public class SaleDetailTableVO implements TableVO {
    /**
     * 时间
     */
    private Date time;
    /**
     * 商品名
     */
    private String productName;
    /**
     * 型号
     */
    private String type;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 总价
     */
    private BigDecimal totalPrice;
    /**
     * 单据类型
     */
    private String sheetType;
}
