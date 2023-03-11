package com.nju.edu.erp.service.promote;

import com.nju.edu.erp.model.po.SaleSheetPO;
import com.nju.edu.erp.service.sheetService.WarehouseService;

import java.math.BigDecimal;

public class PresentPromote implements PromoteStrategy{

    private SaleSheetPO saleSheetPO;

    public PresentPromote(SaleSheetPO saleSheetPO, String presentId, Integer presentNumber, WarehouseService warehouseService){
        this.saleSheetPO = saleSheetPO;
        warehouseService.warehousePresent(presentId, presentNumber, saleSheetPO.getOperator(), saleSheetPO.getId());
    }
    @Override
    public SaleSheetPO doPromotion() {
        if(saleSheetPO.getDiscount()==null) saleSheetPO.setDiscount(new BigDecimal(0));
        if(saleSheetPO.getVoucherAmount()==null) saleSheetPO.setVoucherAmount(new BigDecimal(0));
        return this.saleSheetPO;
    }
}
