package com.nju.edu.erp.model.vo.Sale;

import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.enums.sheetState.WarehousePresentSheetState;
import com.nju.edu.erp.model.vo.ManageProcSheetVO;
import com.nju.edu.erp.model.vo.ManageProcVO;
import com.nju.edu.erp.model.vo.SheetVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetContentVO;
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
public class SaleSheetVO implements SheetVO, ManageProcSheetVO {
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 进货单单据编号（格式为：JHD-yyyyMMdd-xxxxx), 新建单据时前端传null
     */
    private String id;
    /**
     * 供应商id
     */
    private Integer supplier;
    /**
     * 业务员
     */
    private String salesman;
    /**
     * 操作员
     */
    private String operator;
    /**
     * 备注
     */
    private String remark;
    /**
     * 折让前总额
     */
    private BigDecimal rawTotalAmount;
    /**
     * 单据状态, 新建单据时前端传null
     */
    private SaleSheetState state;
    /**
     * 折让后总额, 新建单据时前端传null
     */
    private BigDecimal finalAmount;
    /**
     * 折扣
     */
    private BigDecimal discount;
    /**
     * 使用代金券总额
     */
    private BigDecimal voucherAmount;
    /**
     * 进货单具体内容
     */
    private List<SaleSheetContentVO> saleSheetContent;
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
        return Objects.equals(salesman, salesMan);
    }
    @Override
    public boolean isAllSuccessful(boolean allSuccessful) {
        if(allSuccessful){
            return state== SaleSheetState.SUCCESS;
        }
        return true;
    }
}
