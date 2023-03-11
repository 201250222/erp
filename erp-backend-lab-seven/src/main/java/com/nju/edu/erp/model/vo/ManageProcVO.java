package com.nju.edu.erp.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManageProcVO {
    /**
     * 起始时间，无筛选条件时为null
     */
    Date beginDate;
    /**
     * 截止时间
     */
    Date endDate;
    /**
     * 客户
     */
    private Integer customer;
    /**
     * 业务员
     */
    private String salesman;
    /**
     * 是否只返回审核成功的
     */
    private boolean isAllSuccessful=false;
}
