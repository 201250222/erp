package com.nju.edu.erp.service.promote;


import com.nju.edu.erp.model.po.SaleSheetPO;

import java.math.BigDecimal;

public class DiscountPromote implements PromoteStrategy{

    private SaleSheetPO saleSheetPO;

    public DiscountPromote(SaleSheetPO saleSheetPO, Double discount){
        this.saleSheetPO = saleSheetPO;
        saleSheetPO.setDiscount(new BigDecimal(discount));
    }
    @Override
    public SaleSheetPO doPromotion() {
        if(saleSheetPO.getVoucherAmount()==null) saleSheetPO.setVoucherAmount(new BigDecimal(0));
        return this.saleSheetPO;
    }
}
