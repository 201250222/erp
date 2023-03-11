package com.nju.edu.erp.service.sheetService;

import com.nju.edu.erp.model.vo.SalarySheetVO;
import com.nju.edu.erp.model.vo.UserVO;


import java.util.List;


public interface SalaryService extends SheetService {

    /**
     * @param userVO 制定人
     * @param date 日期
     * @return
     */
    List<SalarySheetVO> makeAllSalarySheets(UserVO userVO, String date);

    SalarySheetVO getSheetById(String salarySheetId);
}
