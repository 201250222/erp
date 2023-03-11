package com.nju.edu.erp.model.vo.inventory;

import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.enums.sheetState.WarehousePresentSheetState;
import com.nju.edu.erp.model.vo.ManageProcSheetVO;
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
public class WarehousePresentSheetVO implements ManageProcSheetVO{
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
     * 单据类型
     */
    private ManageProcType type;
    /**
     * 赠送的总成本
     */
    private BigDecimal value;
    /**
     * 审核状态
     */
    private WarehousePresentSheetState state;

    @Override
    public boolean betweenDate(Date beginDate, Date endDate) {
        if(beginDate == null) return true;
        return createTime.after(beginDate) && createTime.before(endDate);
    }

    @Override
    public boolean isAllSuccessful(boolean allSuccessful) {
        if(allSuccessful){
            return state==WarehousePresentSheetState.SUCCESS;
        }
        return true;
    }
}
