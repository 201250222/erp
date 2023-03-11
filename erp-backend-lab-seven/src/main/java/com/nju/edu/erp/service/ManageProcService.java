package com.nju.edu.erp.service;

import com.nju.edu.erp.model.vo.ManageProcSheetVO;
import com.nju.edu.erp.model.vo.ManageProcVO;
import com.nju.edu.erp.model.vo.RedFlushSheetVO;
import com.nju.edu.erp.model.vo.SheetVO;

import java.util.List;

public interface ManageProcService {
    /**
     * @param manageProcVO 约束条件
     * @return 查询结果
     */
    List<ManageProcSheetVO> searchSale(ManageProcVO manageProcVO);

    /**
     * @param manageProcVO 约束条件
     * @return 查询结果
     */
    List<ManageProcSheetVO> searchPurchase(ManageProcVO manageProcVO);

    /**
     * @param manageProcVO 约束条件
     * @return 查询结果
     */
    List<ManageProcSheetVO> searchFinancial(ManageProcVO manageProcVO);

    /**
     * @param manageProcVO 约束条件
     * @return 查询结果
     */
    List<ManageProcSheetVO> searchInventory(ManageProcVO manageProcVO);

    /**
     * @param redFlushSheetVO 待红冲的单据
     */
    void makeRedFlush(RedFlushSheetVO redFlushSheetVO);

    /**
     * @param redFlushSheetVO 待制作的单据
     */
    void copy(RedFlushSheetVO redFlushSheetVO);
}
