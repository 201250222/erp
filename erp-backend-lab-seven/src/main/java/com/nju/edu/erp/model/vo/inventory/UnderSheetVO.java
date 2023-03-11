package com.nju.edu.erp.model.vo.inventory;

import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.model.vo.ManageProcSheetVO;
import com.nju.edu.erp.model.vo.SheetVO;
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
public class UnderSheetVO implements SheetVO, ManageProcSheetVO {
    /**
     * 库存id
     */
    private Integer warehouseId;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 缺失数量
     */
    private BigDecimal amount;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 创立时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 单据类型
     */
    private ManageProcType type;
}
