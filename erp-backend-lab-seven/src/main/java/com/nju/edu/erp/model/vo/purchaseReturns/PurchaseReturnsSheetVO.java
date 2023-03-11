package com.nju.edu.erp.model.vo.purchaseReturns;


import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.enums.sheetState.PurchaseReturnsSheetState;
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
public class PurchaseReturnsSheetVO implements SheetVO, ManageProcSheetVO {
    /**
     * 进货退货单单据编号（格式为：JHTHD-yyyyMMdd-xxxxx
     */
    private String id;
    /**
     * 关联的进货单id
     */
    private String purchaseSheetId;
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
     * 退货的总额合计
     */
    private BigDecimal totalAmount;
    /**
     * 单据状态
     */
    private PurchaseReturnsSheetState state;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 进货单具体内容
     */
    List<PurchaseReturnsSheetContentVO> purchaseReturnsSheetContent;
    /**
     * 单据类型
     */
    ManageProcType type;
    @Override
    public boolean betweenDate(Date beginDate, Date endDate) {
        if(beginDate == null) return true;
        return createTime.after(beginDate) && createTime.before(endDate);
    }

//    @Override
//    public boolean isCustomer(Integer customer) {
//        if(customer == null) return true;
//        return Objects.equals(operator, customer);
//    }

    @Override
    public boolean isSalesMan(String salesMan) {
        if(salesMan == null) return true;
        return Objects.equals(operator, salesMan);
    }
    @Override
    public boolean isAllSuccessful(boolean allSuccessful) {
        if(allSuccessful){
            return state == PurchaseReturnsSheetState.SUCCESS;
        }
        return true;
    }
}