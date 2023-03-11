package com.nju.edu.erp.service.promote;

import com.nju.edu.erp.model.po.SaleSheetPO;

import java.math.BigDecimal;

public class VoucherPromote implements PromoteStrategy{

    private SaleSheetPO saleSheetPO;

    public VoucherPromote(SaleSheetPO saleSheetPO, BigDecimal voucherPrice){
        this.saleSheetPO = saleSheetPO;
        saleSheetPO.setVoucherAmount(voucherPrice);
    }
    @Override
    public SaleSheetPO doPromotion() {
        if(saleSheetPO.getDiscount()==null) saleSheetPO.setDiscount(new BigDecimal(0));
        return this.saleSheetPO;
    }
}
