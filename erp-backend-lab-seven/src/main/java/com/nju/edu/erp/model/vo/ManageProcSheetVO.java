package com.nju.edu.erp.model.vo;

import java.util.Date;

public interface ManageProcSheetVO{
    default boolean betweenDate(Date beginDate, Date endDate){
        return true;
    };

    default boolean isCustomer(Integer customer){
        return true;
    };

    default boolean isSalesMan(String salesMan){
        return true;
    };

    default boolean isAllSuccessful(boolean allSuccessful){
        return true;
    }
}
