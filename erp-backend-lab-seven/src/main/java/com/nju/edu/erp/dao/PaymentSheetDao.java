package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.PaymentSheetState;
import com.nju.edu.erp.model.po.PaymentSheetContentPO;
import com.nju.edu.erp.model.po.PaymentSheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface PaymentSheetDao {
    // 获取最近一条付款单
    PaymentSheetPO getLatest();

    // 新建付款单
    int addPaymentSheet(PaymentSheetPO paymentSheetPO);

    // 把付款单上的具体内容存入数据库
    void saveBatch(List<PaymentSheetContentPO> paymentSheetContent);

    // 返回所有付款单
    List<PaymentSheetPO> selectAllPaymentSheet();

    // 根据state返回付款单
    List<PaymentSheetPO> selectPaymentSheetByState(PaymentSheetState state);

    // 更新指定id的付款单为指定状态
    int updatePaymentSheet(String paymentSheetId, PaymentSheetState state);

    // 根据id查找付款单
    PaymentSheetPO selectPaymentSheetById(String paymentSheetId);

    // 根据id获取付款单具体内容
    List<PaymentSheetContentPO> selectPaymentSheetContentById(String paymentSheetId);

    String getStateById(String id);

    // 根据起止日期筛选付款单
    List<PaymentSheetPO> selectPaymentSheetByDate(Date beginTime, Date endTime);
}
