package com.nju.edu.erp.service.sheetService;

import com.nju.edu.erp.model.po.PaymentSheetPO;
import com.nju.edu.erp.model.vo.payment.PaymentSheetVO;

import java.util.Date;
import java.util.List;

public interface PaymentService extends SheetService{
    /**
     * 根据id选择付款单
     * @param id 付款单id
     * @return 付款单
     */
    PaymentSheetVO selectPaymentById(String id);

    /**
     * 根据起止日期查询付款单
     * @param beginTime 起始日期
     * @param endTime 终止日期
     * @return 付款单列表
     */
    List<PaymentSheetPO> selectPaymentSheetByDate(Date beginTime, Date endTime);
}
