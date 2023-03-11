package com.nju.edu.erp.model.vo.purchase;


import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import com.nju.edu.erp.enums.sheetState.WarehousePresentSheetState;
import com.nju.edu.erp.model.vo.ManageProcSheetVO;
import com.nju.edu.erp.model.vo.SheetVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseSheetVO implements SheetVO, ManageProcSheetVO {
    /**
     * 进货单单据编号（格式为：JHD-yyyyMMdd-xxxxx), 新建单据时前端传null
     */
    private String id;
    /**
     * 供应商id
     */
    private Integer supplier;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 备注
     */
    private String remark;
    /**
     * 总额合计, 新建单据时前端传null(在后端计算总金额
     */
    private BigDecimal totalAmount;
    /**
     * 单据状态, 新建单据时前端传null
     */
    private PurchaseSheetState state;
    /**
     * 进货单具体内容
     */
    List<PurchaseSheetContentVO> purchaseSheetContent;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 单据类型
     */
    private ManageProcType type;

    @Override
    public boolean betweenDate(Date beginDate, Date endDate) {
        if(beginDate == null) return true;
        return createTime.after(beginDate) && createTime.before(endDate);
    }

    @Override
    public boolean isCustomer(Integer customer) {
        if(customer == null) return true;
        return Objects.equals(supplier, customer);
    }

    @Override
    public boolean isSalesMan(String salesMan) {
        if(salesMan == null) return true;
        return Objects.equals(operator, salesMan);
    }
    @Override
    public boolean isAllSuccessful(boolean allSuccessful) {
        if(allSuccessful){
            return state== PurchaseSheetState.SUCCESS;
        }
        return true;
    }
}
