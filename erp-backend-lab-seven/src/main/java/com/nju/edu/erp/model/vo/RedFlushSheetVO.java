package com.nju.edu.erp.model.vo;

import com.nju.edu.erp.enums.ManageProcType;
import com.nju.edu.erp.model.vo.Sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.SaleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.model.vo.credit.CreditSheetVO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.model.vo.purchaseReturns.PurchaseReturnsSheetVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedFlushSheetVO {
    /**
     * 单据内容
     */
    SaleSheetVO saleSheetVO;
    SaleReturnsSheetVO saleReturnsSheetVO;
    PurchaseSheetVO purchaseSheetVO;
    PurchaseReturnsSheetVO purchaseReturnsSheetVO;
    CreditSheetVO creditSheetVO;
    PaymentSheetVO paymentSheetVO;
    SalarySheetVO salarySheetVO;

    /**
     * 单据类型
     */
    ManageProcType manageProcType;

    /**
     * 用户信息
     */
    UserVO userVO;
}
