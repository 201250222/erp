package com.nju.edu.erp.model.vo.warehouse;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.model.vo.SheetVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseCountingVO implements Serializable {
    /**
     * 库存id
     */
    @Excel(name = "id", width = 25,orderNum = "0")
    private Integer id;

    /**
     * 商品编号
     */
    @Excel(name = "product", width = 25,orderNum = "0")
    private ProductInfoVO product;

    /**
     * 数量
     */
    @Excel(name = "quantity", width = 25,orderNum = "0")
    private Integer quantity;

    /**
     * 进价
     */
    @Excel(name = "purchasePrice", width = 25,orderNum = "0")
    private BigDecimal purchasePrice;

    /**
     * 批次
     */
    @Excel(name = "batchId", width = 25,orderNum = "0")
    private Integer batchId;

    /**
     * 出厂日期
     */
    @Excel(name = "productionDate", width = 25,orderNum = "0")
    private Date productionDate;
}
