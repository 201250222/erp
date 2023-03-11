package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.sheetState.CreditSheetState;
import com.nju.edu.erp.model.po.CreditSheetPO;
import com.nju.edu.erp.model.po.CreditSheetContentPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CreditSheetDao {
    // 获取最近一条进货单
    CreditSheetPO getLatest();

    // 新建收款单
    int addCreditSheet(CreditSheetPO creditSheetPO);

    // 把收款单上的具体内容存入数据库
    void saveBatch(List<CreditSheetContentPO> creditSheetContent);

    // 返回所有收款单
    List<CreditSheetPO> selectAllCreditSheet();

    // 根据state返回收款单
    List<CreditSheetPO> selectCreditSheetByState(CreditSheetState state);

    // 更新指定id的收款单为指定状态
    int updateCreditSheet(String creditSheetId, CreditSheetState creditSheetState);

    // 根据id查找收款单
    CreditSheetPO selectCreditSheetById(String creditSheetId);

    // 根据id获取收款单具体内容
    List<CreditSheetContentPO> selectCreditSheetContentById(String creditSheetId);
}
