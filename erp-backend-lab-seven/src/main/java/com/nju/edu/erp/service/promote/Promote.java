package com.nju.edu.erp.service.promote;

import com.nju.edu.erp.model.po.SaleSheetPO;

public class Promote {
    private PromoteStrategy promoteStrategy;

    public Promote(PromoteStrategy promoteStrategy){
        this.promoteStrategy = promoteStrategy;
    }

    public SaleSheetPO doPromotion(){
        return promoteStrategy.doPromotion();
    }
}
