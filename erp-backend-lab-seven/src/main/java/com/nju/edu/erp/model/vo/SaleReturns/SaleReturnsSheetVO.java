package com.nju.edu.erp.model.vo.SaleReturns;


import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.enums.sheetState.PurchaseReturnsSheetState;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.enums.sheetState.WarehousePresentSheetState;
import com.nju.edu.erp.model.vo.CustomerVO;
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
public class SaleReturnsSheetVO implements SheetVO, ManageProcSheetVO {
    /**
     * 销售退货单单据编号（格式为：XSTHD-yyyyMMdd-xxxxx), 新建单据时前端传null
     */
    private String id;
    /**
     * 关联的销售单id
     */
    private String saleSheetId;
    /**
     * 销售商
     */
    private Integer supplier;
    /**
     * 退货单具体内容
     */
    private List<SaleReturnsSheetContentVO> saleReturnsSheetContent;
    /**
     * 备注
     */
    private String remark;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 单据状态
     */
    private SaleReturnsSheetState state;

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
            return state == SaleReturnsSheetState.SUCCESS;
        }
        return true;
    }
}
