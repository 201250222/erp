package com.nju.edu.erp.service.sheetService;

import com.nju.edu.erp.enums.sheetState.CreditSheetState;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.credit.CreditSheetVO;

import java.util.List;

public interface CreditService extends SheetService{

    /**
     * 根据收款单id获取具体内容
     * @param id 收款单id
     */
    CreditSheetVO selectCreditById(String id);

}
