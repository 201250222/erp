package com.nju.edu.erp.service;

import com.nju.edu.erp.model.po.SaleSheetContentPO;
import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.model.vo.promote.PromoteVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.web.Response;

import java.util.List;

public interface PromoteService {
    /**
     * 制定促销策略
     * @param promoteVO 具体优惠策略
     */
    Response createPromotion(UserVO userVO, PromoteVO promoteVO);

    /**
     * 展示所有促销策略
     */
    List<PromoteVO> showAllPromotions();

    /**
     * 将传入的销售单和其内容选择促销策略并应用
     * @param saleSheetPO
     * @param saleSheetContentPOs
     * @return 补充了信息的销售单
     */
    SaleSheetPO promote(SaleSheetPO saleSheetPO, List<SaleSheetContentPO> saleSheetContentPOs);
}
