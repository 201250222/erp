package com.nju.edu.erp.service.sheetService;


import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.model.vo.SheetVO;
import com.nju.edu.erp.model.vo.UserVO;

import java.util.List;

public interface SheetService {
    /**
     * 制定单据
     * @param userVO
     * @param sheetVO
     */
    void makeSheet(UserVO userVO, SheetVO sheetVO);

    /**
     * 根据状态返回单据
     * @param state
     * @return
     */
    List<SheetVO> getSheetByState(BaseEnum state);

    /**
     * 审批单据
     * @param sheetId
     * @param state
     */
    void approval(String sheetId, BaseEnum state);

}
